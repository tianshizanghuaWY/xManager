package com.qianyang.common.enums;

/**
 * 接口请求状态码
 */
public enum ResultStatusCode {
    SUCCESS("0"),
    FAILURE("1"),
    TIMEOUT("301"),
    EXCEPTION("500");

    private String code;

    private ResultStatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
