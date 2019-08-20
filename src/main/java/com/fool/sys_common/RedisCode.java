package com.fool.sys_common;

public enum RedisCode {

    /**
     * SYSTEM
     */
    SUCCESS("200", "成功"),
    ERROR("400", "失败");

    private String code;
    private String message;

    /**
     * 构造方法
     */
    RedisCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Getter Setter
     */
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
     * key
     */
    // JimdoInvagation // 前台 导航栏 对象
    public static final String JI = "ji";


}
