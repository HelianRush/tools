package com.fool.sys_common;

public enum ApiCode {
    SUCCESS("200", "成功"),
    ERROR("400", "失败");

    private String code;
    private String message;

    ApiCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * API CODE
     */
    // 导航列表
    public static final String JI_LIST = "jis";
    // 注册
    public static final String SIGN_IN = "signin";
}
