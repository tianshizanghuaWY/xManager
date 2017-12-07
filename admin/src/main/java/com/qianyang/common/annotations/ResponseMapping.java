package com.qianyang.common.annotations;

import com.qianyang.common.enums.ResponseType;

import java.lang.annotation.*;

/**
 * 标注在方法上，用于标明该方法用于页面请求，还是 ajax 请求
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseMapping {
    ResponseType type() default ResponseType.JSON;
    String page() default "views/error";
}
