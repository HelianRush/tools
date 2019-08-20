package com.fool.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.fool.entity.FileDoc;
import com.fool.sys_common.SystemCode;
import com.fool.sys_tool.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/pdfCtl")
public class PDFConroller {

    private static Logger logger = LoggerFactory.getLogger(PDFConroller.class);

    @Autowired
    com.fool.service.toDocService toDocService;

    /**
     * PDF上传
     */
    @ResponseBody
    @PostMapping(value = "/pdfUpload")
    public String pdfUpload(MultipartFile pdfFile, HttpServletRequest request, Model model) throws IOException {
        String toType = request.getParameter("toType");
        FileDoc doc = null;
        if (FileTools.isPdf(pdfFile.getContentType())) {
            doc = toDocService.readDoc(pdfFile, toType, model);
        }
        return ((JSONObject) JSONObject.toJSON(doc)).toJSONString();
    }

    /**
     * 下载DCOX
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public String download(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        String jsonString = request.getParameter("fileDoc");
        FileDoc fileDoc = JSONObject.parseObject(jsonString, FileDoc.class);

        File file = new File(ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() + SystemCode.FILE_SYS_PATH_STATIC + fileDoc.getFilePath());

        DownloadService.doDownload(response, model, file, fileDoc.getFileName());

        return "true";
    }
}
