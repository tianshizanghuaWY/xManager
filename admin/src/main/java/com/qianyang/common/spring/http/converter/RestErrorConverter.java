package com.qianyang.common.spring.http.converter;

import com.qianyang.common.exception.domain.RestError;
import org.springframework.core.convert.converter.Converter;

/**
 * <br>
 */
public interface RestErrorConverter<T> extends Converter<RestError, T>{

    @Override
    T convert(RestError restError);
}
