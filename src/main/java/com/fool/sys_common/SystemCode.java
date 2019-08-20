package com.fool.sys_common;

import org.apache.commons.codec.binary.Base64;

import java.util.Date;

public enum SystemCode {

    /**
     *
     */
    SUCCESS("200", "成功"),
    ERROR("400", "失败"),

    /**
     * 图片识别
     */
    // 图片类型 PNG、JPG、JPEG、BMP、GIF
    SUCCESS_IMAGE_PNG("png", "png"),

    SUCCESS_IMAGE_JPG("jpg", "jpg"),

    SUCCESS_IMAGE_JPEG("jpeg", "jpeg"),

    SUCCESS_IMAGE_BMP("bmp", "bmp"),

    SUCCESS_IMAGE_GIF("gif", "gif"),


    ERROR_CODE_IMG001("img001", "不是图片文件"),

    ERROR_CODE_IMG002("img002", "仅支持PNG、JPG、JPEG、BMP、GIF图片");

    private String code;
    private String message;

    /**
     * 构造方法
     */
    SystemCode(String code, String message) {
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
     * 常量
     */
    // 返回消息
    public static final String RETURN_MESSAGE = "message";
    // 文件路径 基础
    public static final String FILE_TEMPPATH_D = "D:/";
    public static final String FILE_CLASSPATH = "classpath:";
    public static final String FILE_SYS_PATH = "/static/temp";
    public static final String FILE_SYS_PATH_STATIC = "/static";
    public static final String FILE_SYS_PATH_IMAGES = "/images";
    public static final String FILE_SYS_PATH_FONT = "/font";
    public static final String FILE_SYS_PATH_TEMP = "/temp";
    public static final String FILE_SYS_PATH_PDF = "/pdfToDoc";
    public static final String FILE_SYS_PATH_PDF_BEFORE = "/before/";
    public static final String FILE_SYS_PATH_PDF_BACK = "/back/";
    public static final String FILE_SYS_PATH_PDF_VIP = "/vip/";

    // 返回文件路径
    public static final String RETURN_PATH = "filePath";
    public static final String RETURN_FILE_NAME = "fileName";

    // 图片类型 PNG、JPG、JPEG、BMP、GIF
    public static final String IMAGE = "image";
    public static final String IMAGE_PNG = "png";
    public static final String IMAGE_JPG = "jpg";
    public static final String IMAGE_JPEG = "jpeg";
    public static final String IMAGE_BMP = "bpm";
    public static final String IMAGE_GIF = "gif";

    // 转换类型
    public static final String TO_TOPE_TXT = "txt";
    public static final String TO_TOPE_DOC = "doc";
    public static final String TO_TOPE_PDF = "pdf";

    // Pdf转doc TOTYPE
    public static final String TO_TOPE_COMMON = "common";
    public static final String TO_TOPE_COMPOSE = "compose";

    // 上传文件 - PDF
    public static final String UPLOAD_PDF = "application/pdf";
    public static final String FILE_PDF = ".pdf";
    public static final String FILE_DOC = ".docx";

    // 获取编码ID
    public static String GetID(Class cla) {
        long time = new Date().getTime();
        String idTemp = cla.getName() + String.valueOf(time);
        Base64 base64 = new Base64();
        return base64.encodeToString(idTemp.getBytes());
    }

    // 获取编码ID
    public static String GetIDL() {
        long time = new Date().getTime();
        return String.valueOf(time);
    }

    // 获取编码ID
    public static Long GetIDLong() {
        return new Date().getTime();
    }

    public static String GetTimestamp() {
        long time = new Date().getTime();
        return String.valueOf(time);
    }

}
