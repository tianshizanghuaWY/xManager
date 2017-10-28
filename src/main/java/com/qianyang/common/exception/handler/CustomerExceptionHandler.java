package com.qianyang.common.exception.handler;

import com.qianyang.common.spring.http.converter.json.DefaultJacksonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 */
@Resource
public class CustomerExceptionHandler extends AbstractHandlerExceptionResolver implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    private List<HttpMessageConverter<?>> messageConverters;
    /*
     * 容器初始化bean 之后，触发该方法完成额外的配置
     */
    @Override
    public void afterPropertiesSet() throws Exception {

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


    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        return null;
    }
}
