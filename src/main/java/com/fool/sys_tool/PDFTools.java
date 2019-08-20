package com.fool.sys_tool;

import com.fool.entity.FileDoc;
import com.fool.sys_common.SystemCode;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDFTools {

    /**
     * 加载PDF文件
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static PDDocument getPDDocument(InputStream inputStream) throws IOException {
        PDFParser parser = new PDFParser(new RandomAccessBuffer(inputStream));
        parser.parse();
        PDDocument pdf = parser.getPDDocument();
        return pdf;
    }

    /**
     * 判断并生成文件夹
     *
     * @param path
     * @return
     */
    private static File existFile(String path) {
        File filePath = new File(path);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }

        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        return filePath;
    }

    /**
     * 创建*.docx文件<br>
     *
     * @param multipartFile
     * @param fileDoc
     * @return
     * @throws IOException
     */
    public static File CreateTempPdfFile(MultipartFile multipartFile, FileDoc fileDoc) throws IOException {

        fileDoc.setFileTimestamp(SystemCode.GetTimestamp());
        fileDoc.setFileName(multipartFile.getOriginalFilename() + SystemCode.FILE_DOC);

        final String path = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() +
                SystemCode.FILE_SYS_PATH +
                SystemCode.FILE_SYS_PATH_PDF +
                SystemCode.FILE_SYS_PATH_PDF_BEFORE +
                fileDoc.getFileTimestamp();

        File filePath = PDFTools.existFile(path);

        File file = new File(filePath.getAbsolutePath(), multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return file;
    }

    /**
     * 编辑*.docx文档<br>
     * 普通
     *
     * @param file
     * @param fileDoc
     * @return
     * @throws IOException
     */
    public static FileDoc toEditDocCommon(File file, FileDoc fileDoc) throws IOException {

        final String path = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() +
                SystemCode.FILE_SYS_PATH +
                SystemCode.FILE_SYS_PATH_PDF +
                SystemCode.FILE_SYS_PATH_PDF_BACK +
                fileDoc.getFileTimestamp();

        File docPath = PDFTools.existFile(path);

        FileOutputStream out = null;

        try {
            PDDocument pdf = PDDocument.load(file);
            PDFTextStripper pdfts = new PDFTextStripper();
            StringBuffer sbContent = new StringBuffer(pdfts.getText(pdf));

            File docFile = new File(docPath.getAbsolutePath(), fileDoc.getFileName());
            if (false == file.exists()) {
                file.mkdirs();
                file.createNewFile();
            }
            out = new FileOutputStream(docFile);

            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragerph = document.createParagraph();
            XWPFRun paragraphRun = paragerph.createRun();
            paragraphRun.setText(sbContent.toString());
            document.write(out);

            // 封账 FileDoc
            fileDoc.setDocToContentSize(sbContent.length());
            fileDoc.setFilePath(SystemCode.FILE_SYS_PATH_TEMP +
                    SystemCode.FILE_SYS_PATH_PDF +
                    SystemCode.FILE_SYS_PATH_PDF_BACK +
                    fileDoc.getFileTimestamp() + "/" +
                    fileDoc.getFileName());
            fileDoc.setFlag(true);
            fileDoc.setDocToContent(sbContent.toString());
            fileDoc.setISDoc(null);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out)
                out.close();
        }

        return fileDoc;
    }

    /**
     * 编辑*.docx文档<br>
     * VIP
     *
     * @param file
     * @param fileDoc
     * @return
     */
    public static FileDoc toEditDocVip(File file, FileDoc fileDoc) throws IOException {

        System.out.println("VIP功能");

        final String path = ResourceUtils.getURL(SystemCode.FILE_CLASSPATH).getPath() +
                SystemCode.FILE_SYS_PATH +
                SystemCode.FILE_SYS_PATH_PDF +
                SystemCode.FILE_SYS_PATH_PDF_BACK +
                SystemCode.FILE_SYS_PATH_PDF_VIP +
                fileDoc.getFileTimestamp();

        File docPath = PDFTools.existFile(path);
        FileOutputStream out = null;

        // PDDocument pdf = PDDocument.load(file);
        PDDocument pdf = PDFTools.getPDDocument(null);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setSortByPosition(true);
        StringBuffer sbContent = new StringBuffer(pdfTextStripper.getText(pdf));
        // PDPageTree pages = pdf.getDocumentCatalog().getPages();
        PDPageTree pages = pdf.getPages();
        PDPage page = pages.get(0);

        PDResources pdResources = page.getResources();

        return fileDoc;
    }

}

