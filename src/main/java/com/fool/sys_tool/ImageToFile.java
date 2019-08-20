package com.fool.sys_tool;

import com.fool.sys_common.SystemCode;
import com.fool.entity.Ocr;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class ImageToFile {

    // 地址
    // private static final String PATH = SystemCode.FILE_PATH;
    private static final String PATH = SystemCode.FILE_SYS_PATH;

    // 地址+文件
    private static String FILE_TEMP;

    private static final String STSong = "STSong-Light";
    private static final String UniGB = "UniGB-UCS2-H";

    /**
     * ********** ********** ********** ********** **********
     *                        TO *.TXT
     * ********** ********** ********** ********** **********
     */

    /**
     * 判断文件是否存在
     * -- 如果存在：不创建
     * -- 如果不存在：创建
     *
     * @return boolean
     * @throws IOException
     */
    private static File existFile(String originalFilename, String toType) throws Exception {

        FILE_TEMP = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() + PATH + "/" + originalFilename + toType;

        File file = new File(FILE_TEMP);
        if (false == file.exists() || false == file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            // file.mkdirs();
            file.createNewFile();
        }
        return file;
    }

    /**
     * JSON数据转StringBuffer
     *
     * @param data
     * @return StringBuffer
     */
    private static StringBuffer GetJsonText(JSONObject data) {
        StringBuffer stringBuff;
        try {
            JSONArray words_result = data.getJSONArray("words_result");
            stringBuff = new StringBuffer();

            for (int i = 0; i < words_result.length(); i++) {
                JSONObject tempJson = (JSONObject) words_result.get(i);
                stringBuff = stringBuff.append(tempJson.get("words").toString());
                stringBuff = stringBuff.append(System.getProperty("line.separator"));
            }
            return stringBuff;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringBuff = new StringBuffer();
    }

    /**
     * 编写.TXT文件
     *
     * @param images
     * @param data
     * @param model
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static Ocr writeTxtFile(MultipartFile images, JSONObject data, Model model) throws IOException, JSONException {

        StringBuffer stringBuff = ImageToFile.GetJsonText(data);
        FileOutputStream fos = null;
        PrintWriter pw = null;
        Ocr ocr = null;

        String originalFilename = images.getOriginalFilename();

        try {
            File file = ImageToFile.existFile(originalFilename, ".txt");

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(stringBuff.toString().toCharArray());

            // 封装转化Ocr对象
            ocr = ImageToFile.toOcrInfo(stringBuff, file);

            model.addAttribute(SystemCode.RETURN_PATH, FILE_TEMP);
            model.addAttribute(SystemCode.RETURN_FILE_NAME, originalFilename + ".txt");

        } catch (Exception exception) {
            model.addAttribute(SystemCode.RETURN_PATH, "");
        } finally {
            pw.flush();
            if (null == pw) {
                pw.close();
            }
            if (null != fos) {
                fos.close();
            }
        }

        return ocr;
    }

    /**
     * ********** ********** ********** ********** **********
     *                        TO *.DOC
     * ********** ********** ********** ********** **********
     */

    /**
     * 编写 Word文件 *.docx
     *
     * @param images
     * @param data
     * @param model
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static Ocr writeDocFile(MultipartFile images, JSONObject data, Model model) throws IOException, JSONException {

        StringBuffer stringBuff = ImageToFile.GetJsonText(data);
        String originalFilename = images.getOriginalFilename();

        FileOutputStream out = null;

        Ocr ocr = null;

        try {
            File file = ImageToFile.existFile(originalFilename, ".docx");
            out = new FileOutputStream(file);
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph title = document.createParagraph();
            XWPFRun titleParagraphRun = title.createRun();

            titleParagraphRun.setText(originalFilename + " 转 Word文档");

            XWPFParagraph paragerph = document.createParagraph();
            XWPFRun paragraphRun = paragerph.createRun();

            paragraphRun.setText(stringBuff.toString());

            document.write(out);

            ocr = ImageToFile.toOcrInfo(stringBuff, file);

            model.addAttribute(SystemCode.RETURN_PATH, FILE_TEMP);
            model.addAttribute(SystemCode.RETURN_FILE_NAME, originalFilename + ".docx");

        } catch (Exception exception) {
            model.addAttribute(SystemCode.RETURN_PATH, "");
            exception.printStackTrace();
        } finally {
            if (null != out)
                out.close();
        }

        return ocr;
    }

    /**
     * ********** ********** ********** ********** **********
     * TO *.PDF
     * ********** ********** ********** ********** **********
     */

    /**
     * 编写 PDF文件 *.pdf
     * 依赖 Adobe iTextPDF
     *
     * @param images
     * @param data
     * @param model
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static Ocr writePdfFile(MultipartFile images, JSONObject data, Model model) throws Exception {

        // 获取转换内容，并以换行符为表示转换为内容数组
        StringBuffer contentByJson = ImageToFile.GetJsonText(data);
        String[] split = contentByJson.toString().split("\r\n");

        // ---------- ---------- ---------- PDDocument Start ---------- ---------- ---------- //
        // 创建*.pdf文件
        File file = ImageToFile.existFile(images.getOriginalFilename(), ".pdf");

        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        // 设置字体 设置中文 解决特殊字符错误 使用系统字体
        PdfFont Microsoft = PdfFontFactory.createFont(ImageToFile.getFontPath(), PdfEncodings.IDENTITY_H, false);

        for (String temp : split) {
            document.add(new Paragraph(temp).setFont(Microsoft));
        }
        // 设置广告信息
        String imagePage = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() + "static/images/webInfo.png";
        Image info = new Image(ImageDataFactory.create(imagePage));
        document.add(new Paragraph().add(info));
        document.add(new Paragraph("Create By www.foolbiubiu.com").setFont(Microsoft));
        // 关闭
        document.close();
        pdfWriter.close();
        // ---------- ---------- ---------- PDDocument End ---------- ---------- ---------- //

        Ocr ocr = new Ocr();
        ocr.setFileName(file.getName());
        ocr.setFilePath(file.getAbsolutePath());
        ocr.setFlag(true);
        ocr.setOcrToContent(contentByJson.toString());
        ocr.setOcrToContentSize(contentByJson.length());

        return ocr;
    }


    /**
     * 编写 PDF文件 *.pdf
     * 依赖 Apache pdfbox
     *
     * @param images
     * @param data
     * @param model
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static Ocr writePdfFileApache(MultipartFile images, JSONObject data, Model model) throws IOException, JSONException {
        // 获取转换内容，并以换行符为表示转换为内容数组
        StringBuffer contentByJson = ImageToFile.GetJsonText(data);
        String[] contentArray = contentByJson.toString().split("\r\n");
        Ocr ocr = null;
        String originalFilename = images.getOriginalFilename();
        try {
            File file = ImageToFile.existFile(originalFilename, ".pdf");
            // fos = new FileOutputStream(file);

            // ---------- ---------- ---------- PDDocument Start ---------- ---------- ---------- //
            // 创建PDF对象
            PDDocument document = new PDDocument();

            // 创建PDF页
            PDPage page = new PDPage();
            PDPage webInfoPage = new PDPage();

            // 设置自定义字体
            String fontPath = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() +
                    SystemCode.FILE_SYS_PATH_STATIC +
                    SystemCode.FILE_SYS_PATH_FONT +
                    "/msyh.ttf";
            PDType0Font FONT_MSYH = PDType0Font.load(document, new File(fontPath));

            // 内容流
            PDPageContentStream stream = new PDPageContentStream(document, page);
            stream.beginText();//开始内容流
            stream.newLineAtOffset(25f, 500); // 设置文本的位置
            // 设置字体格式
            stream.setFont(PDType1Font.TIMES_ROMAN, 14);
            stream.setFont(FONT_MSYH, 9);
            stream.setLeading(14.5f); // 设置文本引导
            for (String row : contentArray) {
                stream.showText(row);
                stream.newLine(); // 换行
            }
            stream.endText();//结束内容流
            stream.close(); // 关闭流

            // ---------- 设置推广 ---------- //
            // 图片、二维码
            String imagePage = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() + "static/images/webInfo.png";
            PDImageXObject image = PDImageXObject.createFromFile(URLDecoder.decode(imagePage, "utf-8"), document);
            // 内容流
            PDPageContentStream webInfoStream = new PDPageContentStream(document, webInfoPage);
            webInfoStream.beginText();//开始内容流
            // 插入图片
            webInfoStream.drawImage(image, 25, 500);
            webInfoStream.newLine(); // 换行
            webInfoStream.newLineAtOffset(25, 800); // 设置文本的位置
            webInfoStream.setFont(FONT_MSYH, 12); // 设置字体格式
            webInfoStream.setLeading(14.5f); // 设置文本引导
            webInfoStream.showText("Create By www.foolbiubiu.com");
            webInfoStream.newLine(); // 换行
            webInfoStream.endText();//结束内容流
            webInfoStream.close(); // 关闭流
            // ---------- PDPageContentStream END PDPageContentStream //

            // 设置PDF文档属性
            PDDocumentInformation documentInfo = document.getDocumentInformation();
            // 时间
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // Set PDDocumentInformation
            documentInfo.setAuthor("www.foolbiubiu.com"); // 作者
            documentInfo.setTitle(images.getName()); // 标题
            documentInfo.setSubject(images.getName()); // 主题
            documentInfo.setKeywords(images.getName() + " By FoolBiuBiu"); // 关键字
            documentInfo.setCreationDate(calendar); // 创建日期
            documentInfo.setModificationDate(calendar); // 修改日期
            documentInfo.setCreator("PDF Application Program"); // 应用程序
            documentInfo.setProducer("FoolBiuBiu Java Apache PDFBox"); // 生产商 制作程序
            // ---------- PDDocumentInformation END ---------- //

            //添加页面到PDF对象
            document.addPage(page);
            document.addPage(webInfoPage);
            document.save(file); // 保存PDF对象到文件
            document.close(); // 关闭PDF对象
            // ---------- ---------- ---------- PDDocument END ---------- ---------- ---------- //

            // 封装转化Ocr对象
            ocr = ImageToFile.toOcrInfo(contentByJson, file);

            model.addAttribute(SystemCode.RETURN_PATH, FILE_TEMP);
            model.addAttribute(SystemCode.RETURN_FILE_NAME, originalFilename + ".pdf");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
        }
        return ocr;
    }

    // 封装ocr转化结果
    public static Ocr toOcrInfo(StringBuffer stringBuff, File file) throws IOException {
        Ocr ocr = new Ocr();
        ocr.setOcrToContent(stringBuff.toString());
        ocr.setOcrToContentSize(stringBuff.length());
        ocr.setFilePath(file.getPath());
        ocr.setFileName(file.getName());
        ocr.setFlag(true);
        // System.out.println(ocr.toString());
        return ocr;
    }

    // 字体
    private static String getFontPath() throws FileNotFoundException {
        return ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() +
                SystemCode.FILE_SYS_PATH_STATIC +
                SystemCode.FILE_SYS_PATH_FONT +
                "/msyh.ttf";
    }

}
