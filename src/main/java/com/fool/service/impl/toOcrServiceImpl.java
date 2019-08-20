package com.fool.service.impl;


import com.baidu.aip.ocr.AipOcr;
import com.fool.sys_tool.BaiduOcr;
import com.fool.sys_tool.FileTools;
import com.fool.sys_tool.ImageToFile;
import com.fool.sys_common.SystemCode;
import com.fool.service.toOcrService;
import com.fool.entity.Ocr;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

@Service
public class toOcrServiceImpl implements toOcrService {

    @Override
    public Ocr readImages(MultipartFile images, String toType, Model model) throws Exception {

        // 图片转为IO流
        InputStream inputStream = images.getInputStream();
        BufferedImage bimg = ImageIO.read(inputStream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Ocr ocr = null;

        // 创建 AIP OCR 实例
        AipOcr client = BaiduOcr.getInstance();

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();

        /*
         * 通用文字识别 50000次/天免费 AipOcrConfig_General_Basic
         * 通用文字识别（高精度版）500次/天免费 AipOcrConfig_Accurate_Basic
         * 通用文字识别（含位置信息版）500次/天免费 AipOcrConfig_General
         * 通用文字识别（含位置高精度版） 50次/天免费 AipOcrConfig_Accurate
         * 通用文字识别（含生僻字版）AipOcrConfig_General_Enhanced
         * 网络图片文字识别 500次/天免费 AipOcrConfig_Webimage
         * 手写文字识别 500次/天免费 AipOcrConfig_Handwriting
         */
        BaiduOcr.AipOcrConfig_General(options);

        // 获取图片类型
        String imageType = null;
        images.getContentType();
        SystemCode systemCode = FileTools.isImage(images.getContentType());
        switch (systemCode) {
            case SUCCESS_IMAGE_BMP:
                imageType = SystemCode.SUCCESS_IMAGE_BMP.getMessage();
                break;
            case SUCCESS_IMAGE_GIF:
                imageType = SystemCode.SUCCESS_IMAGE_GIF.getMessage();
                break;
            case SUCCESS_IMAGE_JPEG:
                imageType = SystemCode.SUCCESS_IMAGE_JPEG.getMessage();
                break;
            case SUCCESS_IMAGE_JPG:
                imageType = SystemCode.SUCCESS_IMAGE_JPG.getMessage();
                break;
            case SUCCESS_IMAGE_PNG:
                imageType = SystemCode.SUCCESS_IMAGE_PNG.getMessage();
                break;
        }

        // 参数为二进制数组
        ImageIO.write(bimg, imageType, baos);
        byte[] file = baos.toByteArray();

        //调用 图文转换
        JSONObject res = client.basicGeneral(file, options);

        // System.out.println(res.toString(2));


        // 判断勾选类型 选择相应的文件生成
        switch (toType) {
            case SystemCode.TO_TOPE_TXT:
                ocr = ImageToFile.writeTxtFile(images, res, model);
                break;
            case SystemCode.TO_TOPE_DOC:
                ocr =ImageToFile.writeDocFile(images, res, model);
                break;
            case SystemCode.TO_TOPE_PDF:
                ocr =ImageToFile.writePdfFile(images, res, model);
                break;
        }

        return ocr;
    }

}
