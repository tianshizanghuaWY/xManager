package com.qianyang.common.exception.resolver.impl;

import com.qianyang.common.exception.domain.RestError;
import com.qianyang.common.exception.resolver.ErrorResolver;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * <br>
 */
public class RestErrorResolver implements ErrorResolver<RestError>{

    @Override
    public RestError errorResolver(ServletWebRequest request, Object handler, Exception ex) {
        return null;
    }
}
