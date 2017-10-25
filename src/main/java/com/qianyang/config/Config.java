package com.qianyang.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/*
 * @Configuration 标识该类为配置类
 * @ComponentScan 自动扫描包下所有使用 @Servive @Componet @Repository @Controller 注解的类
 * 并注册为Bean
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.qianyang")
public class Config extends WebMvcConfigurationSupport {

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
}


