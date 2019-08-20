package com.fool.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.fool.sys_tool.FileTools;
import com.fool.sys_common.SystemCode;
import com.fool.entity.Ocr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * OCR
 */
@Controller
@RequestMapping(value = "/ocrCtl")
public class OCRConroller {
    private static Logger logger = LoggerFactory.getLogger(OCRConroller.class);

    @Autowired
    private com.fool.service.toOcrService toOcrService;

    /**
     * 图片上传
     */
    @ResponseBody
    @PostMapping(value = "/fileUpload")
    public String fileUpload(MultipartFile files, HttpServletRequest request, Model model) throws Exception {

        SystemCode systemCode = FileTools.isImage(files.getContentType());
        String toType = request.getParameter("toType");

        Ocr ocr = null;

        switch (systemCode) {
            case ERROR_CODE_IMG001:
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.ERROR_CODE_IMG001.getMessage());
                break;

            case ERROR_CODE_IMG002:
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.ERROR_CODE_IMG002.getMessage());
                break;

            case SUCCESS_IMAGE_BMP:
            case SUCCESS_IMAGE_GIF:
            case SUCCESS_IMAGE_JPEG:
            case SUCCESS_IMAGE_JPG:
            case SUCCESS_IMAGE_PNG:
                // 图片类型 PNG、JPG、JPEG、BMP
                ocr = toOcrService.readImages(files, toType, model);
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.SUCCESS.getMessage());
                break;

            default:
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.ERROR.getMessage());
        }

        return ((JSONObject) JSONObject.toJSON(ocr)).toJSONString();
    }

    /**
     * 下载
     *
     * @param request
     * @param response
     * @param model
     * @return URL
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public String download(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String filePath = request.getParameter("filePath");
        String fileName = request.getParameter("fileName");

        File file = new File(filePath);

        DownloadService.doDownload(response,model,file, fileName);

        return "true";
    }

}
