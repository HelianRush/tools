package com.fool.controller;

import com.fool.sys_common.SystemCode;
import com.fool.service.toOcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/uploadCtl")
public class UploadController {

    @Autowired
    private toOcrService toOcrService;

    /**
     * 显示页面
     *
     * @param model
     * @return String
     */
    @RequestMapping(value = "/showUpload")
    public String showUpload(Model model) {
        model.addAttribute(SystemCode.RETURN_MESSAGE, "");
        return "upload";
    }

    /**
     * 图片上传,并且转换
     *
     * @param images
     * @return String
     * @throws IOException
     */
    @RequestMapping(value = "/uploadImage")
    public String uploadImage(MultipartFile images, HttpServletRequest request, Model model) throws Exception {

        /*
         * 获取上传文件
         * 1.判断文件类型，匹配是否为图片
         * 2.获取转换类型
         * 3.获取图片，进行图文识别
         * */

        // 判断文件类型，匹配是否为图片
//        SystemCode systemCode = FileTools.isImage(images.getContentType());
        /*
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
                toOcrService.readImages(images, request, model);
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.SUCCESS.getMessage());
                break;

            default:
                model.addAttribute(SystemCode.RETURN_MESSAGE, SystemCode.ERROR.getMessage());
        }
        */

        return "upload";
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
    @RequestMapping(value = "/download")
    public String uploadImage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        /*
        String filePath = request.getParameter("filePath");
        String fileName = request.getParameter("fileName");

        System.out.println(filePath);
        System.out.println(fileName);

        File file = new File(filePath);

        // response.setContentType("application/force-download");// 设置强制下载不打开
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        response.addHeader("Content-Length", FileTools.CountByte(file));

        byte[] buffer = new byte[FileTools.ReadBytes(file)];

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            os = response.getOutputStream();

            int i = bis.read(buffer);

            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }

            // 返回成功信息
            model.addAttribute(SystemCode.SUCCESS.getCode(), SystemCode.SUCCESS.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == bis) {
                bis.close();
            }
            if (null == fis) {
                fis.close();
            }
            os.flush();
            if (null == os) {
                os.close();
            }
        }
*/
        return "upload";


    }

}

