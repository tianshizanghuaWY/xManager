package com.qianyang.config;


import com.qianyang.common.spring.http.converter.json.DefaultJacksonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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

    @Bean
    public DefaultJacksonHttpMessageConverter defaultJacksonHttpMessageConverter(){
        return new DefaultJacksonHttpMessageConverter();
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


