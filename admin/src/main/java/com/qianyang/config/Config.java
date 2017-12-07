package com.qianyang.config;


import com.qianyang.common.exception.handler.CustomerExceptionHandler;
import com.qianyang.common.exception.resolver.ErrorResolver;
import com.qianyang.common.exception.resolver.impl.RestErrorResolver;
import com.qianyang.common.spring.http.converter.impl.MapRestErrorConverter;
import com.qianyang.common.spring.http.converter.json.DefaultJacksonHttpMessageConverter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.List;

/*
 * @Configuration 标识该类为配置类
 * @ComponentScan 自动扫描包下所有使用 @Servive @Componet @Repository @Controller 注解的类
 * 并注册为Bean
 *
 * !!! 不要继承自 WebMvcConfigurationSupport, 不然 extendMessageConverters() 不会起作用
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.qianyang")
public class Config extends WebMvcConfigurerAdapter {

    /*
     * JSP 视图解析器
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    /*
     * 设置 JSON HttpMessageConverter
     */
    @Bean
    public DefaultJacksonHttpMessageConverter defaultJacksonHttpMessageConverter(){
        return new DefaultJacksonHttpMessageConverter();
    }

    /*
     * 设置异常解析器
     */
    @Bean
    public RestErrorResolver errorResolver(){
        RestErrorResolver resolver = new RestErrorResolver();
        resolver.setLocaleResolver(localResolver());

        return resolver;
    }

    @Bean
    public AcceptHeaderLocaleResolver localResolver(){
        return new AcceptHeaderLocaleResolver();
    }

    @Bean
    public AnnotationMethodHandlerExceptionResolver annotationMethodHandlerExceptionResolver(){
        AnnotationMethodHandlerExceptionResolver annotationMethodHandlerExceptionResolver
                = new AnnotationMethodHandlerExceptionResolver();

        //Allow Exceptions to be handled in annotated methods if desired.  Otherwise fallback to the
        //'restExceptionResolver' below:
        annotationMethodHandlerExceptionResolver.setOrder(0);

        return annotationMethodHandlerExceptionResolver;
    }

    @Bean
    public CustomerExceptionHandler customerExceptionHandler(){
        CustomerExceptionHandler customerExceptionHandler = new CustomerExceptionHandler();

        customerExceptionHandler.setOrder(100);

        customerExceptionHandler.setErrorConverter(new MapRestErrorConverter());
        customerExceptionHandler.setErrorResolver(errorResolver());

        return customerExceptionHandler;
    }


      /*
       * 重写该方法会直接去除 spring 默认提供的 message converter
       */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        converters.add(defaultJacksonHttpMessageConverter());
//    }

    /*
     * 增加自定义的 Message converter
     * 同时将它放在 converter 链的首位, 这样可以减少调用链的执行， 测试中....
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);

        //converters.add(defaultJacksonHttpMessageConverter());
        converters.add(0, defaultJacksonHttpMessageConverter());
    }

}


