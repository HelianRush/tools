package com.fool.controller.api;

import com.fool.sys_common.SystemCode;
import com.fool.sys_tool.FileTools;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadService {

    public static boolean doDownload(HttpServletResponse response, Model model, File file, String fileName) throws IOException {

        // response.setContentType("application/force-download");// 设置强制下载不打开
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        response.addHeader("Content-Length", FileTools.CountByte(file));

        byte[] buffer = new byte[FileTools.ReadBytes(file)];

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        boolean exists = file.exists();
        System.out.println(exists);
        if (exists) {
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

                System.gc();
                file.delete();
            }
        }

        return true;
    }

}
