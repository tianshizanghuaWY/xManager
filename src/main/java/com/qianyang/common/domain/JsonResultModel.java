package com.qianyang.common.domain;

import com.qianyang.common.enums.ResultStatusCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <br>
 */
public class JsonResultModel<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    private ResultStatusCode code;
    private T data;
    private HttpStatus httpStatus;
    private String msg;

    public JsonResultModel() {
        super();
    }

    public JsonResultModel(T data) {
        super();
        this.data = data;
    }
}
