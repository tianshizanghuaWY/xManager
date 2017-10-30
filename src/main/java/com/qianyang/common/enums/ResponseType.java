package com.qianyang.common.enums;

/**
 * 请求类型
 */
public enum ResponseType {
    PAGE("页面请求"),
    JSON("Ajax 请求");

    private String desc;

    private ResponseType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
