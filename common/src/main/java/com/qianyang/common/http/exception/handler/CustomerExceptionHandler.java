package com.qianyang.common.http.exception.handler;

import com.qianyang.common.http.annotations.ResponseMapping;
import com.qianyang.common.http.converter.RestErrorConverter;
import com.qianyang.common.http.converter.json.DefaultJacksonHttpMessageConverter;
import com.qianyang.common.http.enums.ResponseType;
import com.qianyang.common.http.exception.RestError;
import com.qianyang.common.http.exception.resolver.impl.RestErrorResolver;
import com.qianyang.common.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <br>
 */
public class CustomerExceptionHandler extends AbstractHandlerExceptionResolver implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    private List<HttpMessageConverter<?>> messageConverters;

    private RestErrorResolver errorResolver;
    private RestErrorConverter<?> errorConverter;

    public CustomerExceptionHandler(){

    }
    /*
     * 容器初始化bean 之后，触发该方法完成额外的配置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ensureMessageConvertersInited();
    }

    private void ensureMessageConvertersInited(){
        logger.info("init HttpMessageConverters for CustomerExceptionHandler....");

        messageConverters = new ArrayList<>();

        //先置入自己定制的
        messageConverters.add(new DefaultJacksonHttpMessageConverter());
        //再加入 Spring 提供的默认的HttpMessageConverter
        new HttpMessageConverterHelper().addDefaults(messageConverters);
    }

    //利用 spring 自身的'设置' 行为, 增加一些基础的 MessageConverter 到 converters
    //leverage Spring's existing default setup behavior:
    private static final class HttpMessageConverterHelper extends WebMvcConfigurationSupport {
        public void addDefaults(List<HttpMessageConverter<?>> converters) {
            addDefaultHttpMessageConverters(converters);
        }
    }

    /**
     * Actually resolve the given exception that got thrown during on handler execution, returning a ModelAndView that
     * represents a specific error page if appropriate.
     * <p/>
     * May be overridden in subclasses, in order to apply specific
     * exception checks. Note that this template method will be invoked <i>after</i> checking whether this resolved applies
     * ("mappedHandlers" etc), so an implementation may simply proceed with its actual exception handling.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the executed handler, or <code>null</code> if none chosen at the time of the exception (for example,
     *                 if multipart resolution failed)
     * @param ex       the exception that got thrown during handler execution
     * @return a corresponding ModelAndView to forward to, or <code>null</code> for default processing
     */
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        ServletWebRequest webRequest = new ServletWebRequest(request, response);

        //将异常转换成  RestError
        RestError error = errorResolver.errorResolver(webRequest, handler, ex);
        if (error == null) {
            //表示该 Handler 不会处理当前抛出的异常
            return null;
        }

        ModelAndView mav = null;

        try {
            mav = getModelAndView(webRequest, handler, error);
        } catch (Exception invocationEx) {
            logger.error("Acquiring ModelAndView for Exception [" + ex + "] resulted in an exception.", invocationEx);
        }

        return mav;
    }

    protected ModelAndView getModelAndView(ServletWebRequest webRequest, Object handler,
                                           RestError error) throws Exception {

        applyStatusIfPossible(webRequest, error);

        //default the error instance in case they don't configure an error converter
        Object body = error;

        RestErrorConverter converter = getErrorConverter();

        if (converter != null) {
            //将 RestError 转换成指定的 body(一般都是 map)
            body = converter.convert(error);
        }

        return handleResponseBody(body, webRequest, handler);
    }

    private void applyStatusIfPossible(ServletWebRequest webRequest, RestError error) {
        if (!WebUtils.isIncludeRequest(webRequest.getRequest())) {
            webRequest.getResponse().setStatus(error.getStatus().value());
        }
        //TODO support response.sendError ?
    }

    /*
     * 处理 response
     * 1) 获得请求所接受的返回内容之媒体类型  Accept
     * 2) 利用该 Handler 指定的 MessageConverter
     * 3) 循环没一个 MessageConverter 看是否能处理该请求，判断标准 messageConverter.canWrite() 返回true
     * 4) messageConverter.canWrite()
     *    1) request accept 之 MediaType 为空或者 All 返回true
     *    2) 循环判断 messageConverter 的 supportMediaTypes 是否兼容 accept MediaType
     *       1) 先判断 2 个 MediaType 是否的 type是否为通配符 (*), 是则返回 true
     *       2) 否则就要继续判断了  逻辑见 MediaType.isCompatibleWith(MediaType other)
     *
     *  只有 MessageConverter 所支持的 MediaType 能够兼容 Request-Accept-MediaType，该MessageConverter 才能处理该请求
     *  才能将 body 写入到 response
     */
    @SuppressWarnings("unchecked")
    private ModelAndView handleResponseBody(Object body, ServletWebRequest webRequest,Object handler)
            throws ServletException, IOException {

        HttpInputMessage inputMessage = new ServletServerHttpRequest(webRequest.getRequest());

        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }

        //Accept MediaType 按照质量因子(q)排序
        MediaType.sortByQualityValue(acceptedMediaTypes);

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());

        Class<?> bodyType = body.getClass();

        List<HttpMessageConverter<?>> converters = this.messageConverters;

        if (converters != null) {
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                for (HttpMessageConverter messageConverter : converters) {
                    if (messageConverter.canWrite(bodyType, acceptedMediaType)) {

                        //判断页面请求还是 ajax 请求
                        String errorPage = getResponsePage(handler);
                        if(ValidatorUtil.isNotEmpty(errorPage)){
                            return new ModelAndView(errorPage);
                        }else{
                            messageConverter.write(body, acceptedMediaType, outputMessage);

                            //return empty model and view to short circuit the iteration and to let
                            //Spring know that we've rendered the view ourselves:
                            return new ModelAndView();
                        }

                    }
                }
            }
        }

        if (logger.isWarnEnabled()) {
            logger.warn("Could not find HttpMessageConverter that supports return type [" + bodyType +
                    "] and " + acceptedMediaTypes);
        }

        return null;
    }

    public RestErrorResolver getErrorResolver() {
        return errorResolver;
    }

    public void setErrorResolver(RestErrorResolver errorResolver) {
        this.errorResolver = errorResolver;
    }

    public RestErrorConverter<?> getErrorConverter() {
        return errorConverter;
    }

    public void setErrorConverter(RestErrorConverter<?> errorConverter) {
        this.errorConverter = errorConverter;
    }

    //返回错误页面
    private String getResponsePage(Object handler){
        if(handler == null)
            return null;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        ResponseMapping responseMapping
                = AnnotationUtils.findAnnotation(method, ResponseMapping.class);
        if(responseMapping == null){
            if(isJsonRequest(handlerMethod)){
                return null;
            }else{
                return "views/error";
            }
        }

        if(ResponseType.PAGE == responseMapping.type()){
            return responseMapping.page();
        }

        return null;
    }

    private boolean isJsonRequest(HandlerMethod handlerMethod){
        Method method = handlerMethod.getMethod();

        //返回类型是 void 的
        @SuppressWarnings("rawtypes")
        Class returnType = method.getReturnType();
        if(returnType != null && "void".equals(returnType.getName())){
            return true;
        }

        //是否使用了 ResponseBody 注解
        ResponseBody responseBodyAnn
                = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if(responseBodyAnn == null){
            //判断contrller 上是否使用了@ReponseBody 注解
            responseBodyAnn
                    = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), ResponseBody.class);
        }

        if(responseBodyAnn != null)
            return true;

        return false;
    }
}
