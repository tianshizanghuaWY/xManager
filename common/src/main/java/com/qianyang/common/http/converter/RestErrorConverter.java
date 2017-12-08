package com.qianyang.common.http.converter;

import com.qianyang.common.http.exception.RestError;
import org.springframework.core.convert.converter.Converter;

/**
 * <br>
 */
public interface RestErrorConverter<T> extends Converter<RestError, T>{

    @Override
    T convert(RestError restError);
}
