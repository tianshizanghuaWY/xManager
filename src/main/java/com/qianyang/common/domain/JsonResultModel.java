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

    public JsonResultModel(ResultStatusCode code){
        super();
        this.code = code;
    }

    public JsonResultModel(T data) {
        super();
        this.data = data;
    }

    public JsonResultModel status(HttpStatus status){
        this.httpStatus = status;
        return this;
    }
    public JsonResultModel code(ResultStatusCode code){
        this.code = code;
        return this;
    }



    //---------------------------- getter / setter --------------------------
    public ResultStatusCode getCode() {
        return code;
    }

    public void setCode(ResultStatusCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
