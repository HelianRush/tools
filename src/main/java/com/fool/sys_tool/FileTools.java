package com.fool.sys_tool;

import com.fool.sys_common.SystemCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Component
public class FileTools {

    private static Logger logger = LoggerFactory.getLogger(FileTools.class);

    /**
     * 匹配是否可以转换
     *
     * @param contentType
     * @return SystemCode
     */
    public static SystemCode isImage(String contentType) {

        String[] typeName = contentType.split("/");

        if (typeName[0].equals(SystemCode.IMAGE)) {
            switch (typeName[1]) {
                case SystemCode.IMAGE_BMP:
                    return SystemCode.SUCCESS_IMAGE_BMP;

                case SystemCode.IMAGE_GIF:
                    return SystemCode.SUCCESS_IMAGE_GIF;

                case SystemCode.IMAGE_JPEG:
                    return SystemCode.SUCCESS_IMAGE_JPEG;

                case SystemCode.IMAGE_JPG:
                    return SystemCode.SUCCESS_IMAGE_JPG;

                case SystemCode.IMAGE_PNG:
                    return SystemCode.SUCCESS_IMAGE_PNG;

                default:
                    return SystemCode.ERROR_CODE_IMG002;
            }
        } else {
            return SystemCode.ERROR_CODE_IMG001;
        }

    }

    /**
     * 匹配是否为PDF文件
     *
     * @param contentType
     * @return
     */
    public static boolean isPdf(String contentType) {
        if (SystemCode.UPLOAD_PDF.equals(contentType)) {
            return true;
        }
        return false;
    }

    /**
     * 计算明流长度
     *
     * @param file
     * @return String
     */
    public static String CountByte(File file) {
        return String.valueOf(file.length());
    }

    /**
     * 计算输入流偏移长度
     *
     * @param file
     * @return int
     */
    public static int ReadBytes(File file) {
        return Integer.valueOf(String.valueOf(file.length()));
    }

    /**
     * 定时删除temp/*.files
     * 秒 分 时 天 月 星期 年
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void reportCurrentTime() throws Exception {

        // GC
        System.gc();

        // 时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date date = new Date();

        // Path = classpath:/static/temp/
        String path = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() + SystemCode.FILE_SYS_PATH;

        // 文件夹
        File fils = new File(path);

        // 目录存在
        if (fils.isDirectory()) {
            String[] filesList = fils.list();
            int deleteCount = 0;
            for (int i = 0; i < filesList.length; i++) {
                File file = new File(fils + "\\" + filesList[i]);
                if (file.delete()) {
                    deleteCount++;
                }
            }

            logger.info("执行定时删除");
            logger.info("执行时间：" + sdf.format(date));
            logger.info("文件个数：" + filesList.length);
            logger.info("删除文件个数：" + deleteCount);
            logger.info("任务完成");
        }

    }
}
