package com.fool.sys_tool;

import com.baidu.aip.ocr.AipOcr;

import java.util.HashMap;

public class BaiduOcr {

    /**
     * 请求限制
     * 请求图片需经过base64编码：图片的base64编码指将一副图片数据编码成一串字符串，使用该字符串代替图像地址。您可以首先得到图片的二进制，然后再进行urlencode。
     * <p>
     * 注意：图片的base64编码是不包含图片头的，如（data:image/jpg;base64,）
     * <p>
     * 请求格式支持：PNG、JPG、JPEG、BMP
     * <p>
     * 百度文字识别所有接口的图像大小限制:base64编码urlencode后大小不超过4M，最短边至少15px，最长边最大4096px
     * <p>
     * <p>
     * 图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持jjpg/jpeg/png/bmp格式，当image字段存在时url字段失效
     * <p>
     * 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/jpeg/png/bmp格式，当image字段存在时url字段失效，不支持https的图片链接
     * <p>
     * 识别语言类型，默认为CHN_ENG。可选值包括：CHN_ENG：中英文混合；ENG：英文；POR：葡萄牙语；FRE：法语；GER：德语；ITA：意大利语；SPA：西班牙语；RUS：俄语；JAP：日语；KOR：韩语
     */

    //设置APPID/AK/SK
    private static final String APP_ID = "16152603";
    private static final String API_KEY = "zvFY66cE9h0YDjtEkGLRlbKm";
    private static final String SECRET_KEY = "SBMrG8mGEHLU9jD1tgXBcCY6loC8FADb";

    // 初始化 AipOcr
    private static AipOcr client;

    public static synchronized AipOcr getInstance() {
        if (null == client) {
            client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
            // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
            // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

            // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
            // 也可以直接通过jvm启动参数设置此环境变量
            // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        }
        return client;
    }

    /**
     * 通用文字识别 50000次/天免费
     * 用户向服务请求识别某张图中的所有文字。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic
     * HashMap<String, String>
     *
     * @param options
     */
    public static void AipOcrConfig_General_Basic(HashMap<String, String> options) {
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
    }

    /**
     * 通用文字识别（高精度版）500次/天免费
     * 用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic
     *
     * @param options
     */
    public static void AipOcrConfig_Accurate_Basic(HashMap<String, String> options) {
        options.put("detect_direction", "true");
        options.put("probability", "true");
    }

    /**
     * 通用文字识别（含位置信息版）500次/天免费
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图中的位置信息。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/general
     *
     * @param options
     */
    public static void AipOcrConfig_General(HashMap<String, String> options) {
        options.put("recognize_granularity", "big");
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");
    }

    /**
     * 通用文字识别（含位置高精度版） 50次/天免费
     * 用户向服务请求识别某张图中的所有文字，并返回文字在图片中的坐标信息，相对于通用文字识别（含位置信息版）该产品精度更高，但是识别耗时会稍长。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/accurate
     *
     * @param options
     */
    public static void AipOcrConfig_Accurate(HashMap<String, String> options) {
        options.put("recognize_granularity", "big");
        options.put("detect_direction", "true");
        options.put("vertexes_location", "true");
        options.put("probability", "true");
    }

    /**
     * 通用文字识别（含生僻字版）
     * 某些场景中，图片中的中文不光有常用字，还包含了生僻字，这时用户需要对该图进行文字识别，应使用通用文字识别（含生僻字版）。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/general_enhanced
     *
     * @param options
     */
    public static void AipOcrConfig_General_Enhanced(HashMap<String, String> options) {
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
    }

    /**
     * 网络图片文字识别 500次/天免费
     * 用户向服务请求识别一些网络上背景复杂，特殊字体的文字。
     * https://aip.baidubce.com/rest/2.0/ocr/v1/webimage
     *
     * @param options
     */
    public static void AipOcrConfig_Webimage(HashMap<String, String> options) {
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
    }


    /**
     * 手写文字识别 500次/天免费
     * 对手写中文汉字、数字进行识别
     * https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting
     *
     * @param options
     */
    public static void AipOcrConfig_Handwriting(HashMap<String, String> options) {
        options.put("recognize_granularity", "big");
    }


}
