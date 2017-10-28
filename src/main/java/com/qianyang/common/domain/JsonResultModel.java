package com.qianyang.common.domain;

import com.qianyang.common.enums.ResultStatusCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <br>
 */
public class JsonResultModel<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    //private ResultStatusCode codeMsg;
    private int code = 0;
    private T data;
    private HttpStatus httpStatus;
    private String msg;

    public JsonResultModel() {
        super();
    }

    public JsonResultModel(ResultStatusCode codeMsg){
        super();
        //this.codeMsg = codeMsg;
        this.code = codeMsg.getCode();
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
        //this.codeMsg = code;
        this.code = code.getCode();
        return this;
    }



    //---------------------------- getter / setter --------------------------
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    /*public ResultStatusCode getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(ResultStatusCode codeMsg) {
        this.codeMsg = codeMsg;
    }*/
}
