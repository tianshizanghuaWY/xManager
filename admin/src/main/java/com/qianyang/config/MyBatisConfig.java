package com.qianyang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <br>
 * 配置类， 仅仅用于加载 Mybatis 相关的bean
 */
@Configuration
@ImportResource("classpath:spring-mybatis.xml")
public class MyBatisConfig {
}
