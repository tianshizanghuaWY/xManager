package com.qianyang.common.exception.resolver;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * <br>
 */
public interface ErrorResolver<T> {

    /*
     * 将异常转换成 Bean
     * @request 可以用来获取当前请求的 request / response 对
     * @handler 抛出异常到controller 处理方法, 也可能是null
     * @ex 所抛出的异常
     */
    public T errorResolver(ServletWebRequest request, Object handler, Exception ex);
}
