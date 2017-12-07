package com.qianyang.common.enums;

/**
 * 接口请求状态码
 */
public enum ResultStatusCode {
    SUCCESS(0),
    FAILURE(1),
    TIMEOUT(301),
    EXCEPTION(500);

    private int code;

    private ResultStatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
