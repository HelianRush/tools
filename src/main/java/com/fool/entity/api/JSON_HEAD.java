package com.fool.entity.api;

import java.util.Map;

public class JSON_HEAD {

    private String requestMsg;
    private String requestCode;
    private String responseMsg;
    private String responseCode;
    private String apiCode;
    private Map<String, Object> parameters;

    public JSON_HEAD() {
    }

    public JSON_HEAD(String requestMsg, String requestCode, String responseMsg, String responseCode, String apiCode, Map<String, Object> parameters) {
        this.requestMsg = requestMsg;
        this.requestCode = requestCode;
        this.responseMsg = responseMsg;
        this.responseCode = responseCode;
        this.apiCode = apiCode;
        this.parameters = parameters;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "JSON_HEAD{" +
                "requestMsg='" + requestMsg + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", apiCode='" + apiCode + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
