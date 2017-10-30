package com.qianyang.common.spring.http.converter.impl;

import com.qianyang.common.exception.domain.RestError;
import com.qianyang.common.spring.http.converter.RestErrorConverter;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RestError -> Map
 * 用于 response 输出
 */
public class MapRestErrorConverter implements RestErrorConverter<Map> {
    private static final String DEFAULT_STATUS_KEY = "status";
    private static final String DEFAULT_CODE_KEY = "code";
    private static final String DEFAULT_MESSAGE_KEY = "message";
    private static final String DEFAULT_DEVELOPER_MESSAGE_KEY = "developerMessage";
    private static final String DEFAULT_MORE_INFO_URL_KEY = "moreInfoUrl";

    private String statusKey = DEFAULT_STATUS_KEY;
    private String codeKey = DEFAULT_CODE_KEY;
    private String messageKey = DEFAULT_MESSAGE_KEY;
    private String developerMessageKey = DEFAULT_DEVELOPER_MESSAGE_KEY;
    private String moreInfoUrlKey = DEFAULT_MORE_INFO_URL_KEY;

    @Override
    public Map convert(RestError re) {
        Map<String, Object> m = createMap();

        int code = re.getCode();
        if (code > 0) {
            m.put(getCodeKey(), code);
        }

        HttpStatus status = re.getStatus();
        m.put(getStatusKey(), status.value());

        String message = re.getMessage();
        if (message != null) {
            m.put(getMessageKey(), message);
        }

        String devMsg = re.getDeveloperMessage();
        if (devMsg != null) {
            m.put(getDeveloperMessageKey(), devMsg);
        }

        String moreInfoUrl = re.getMoreInfoUrl();
        if (moreInfoUrl != null) {
            m.put(getMoreInfoUrlKey(), moreInfoUrl);
        }

        return m;
    }

    protected Map<String, Object> createMap() {
        return new LinkedHashMap<String, Object>();
    }

    public String getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(String statusKey) {
        this.statusKey = statusKey;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getDeveloperMessageKey() {
        return developerMessageKey;
    }

    public void setDeveloperMessageKey(String developerMessageKey) {
        this.developerMessageKey = developerMessageKey;
    }

    public String getMoreInfoUrlKey() {
        return moreInfoUrlKey;
    }

    public void setMoreInfoUrlKey(String moreInfoUrlKey) {
        this.moreInfoUrlKey = moreInfoUrlKey;
    }
}