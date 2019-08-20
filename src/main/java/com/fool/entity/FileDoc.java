package com.fool.entity;

import java.io.InputStream;
import java.io.Serializable;

public class FileDoc implements Serializable {

    // 转换后的内容
    private String DocToContent;
    // 字数
    private long DocToContentSize;
    // 文件地址
    private String FilePath;
    // 文件名
    private String FileName;
    // 转换结果
    private boolean Flag;
    // 二进制文件
    private InputStream ISDoc;
    // 时间戳
    private String FileTimestamp;
    // PDF页数
    private int PdfPageSize;
    // DOC页数
    private int DocPageSize;

    //
    public FileDoc() {
    }

    // Getter Setter
    public String getDocToContent() {
        return DocToContent;
    }

    public void setDocToContent(String docToContent) {
        DocToContent = docToContent;
    }

    public long getDocToContentSize() {
        return DocToContentSize;
    }

    public void setDocToContentSize(long docToContentSize) {
        DocToContentSize = docToContentSize;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public boolean isFlag() {
        return Flag;
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    public InputStream getISDoc() {
        return ISDoc;
    }

    public void setISDoc(InputStream ISDoc) {
        this.ISDoc = ISDoc;
    }

    public String getFileTimestamp() {
        return FileTimestamp;
    }

    public void setFileTimestamp(String fileTimestamp) {
        FileTimestamp = fileTimestamp;
    }

    public int getPdfPageSize() {
        return PdfPageSize;
    }

    public void setPdfPageSize(int pdfPageSize) {
        PdfPageSize = pdfPageSize;
    }

    public int getDocPageSize() {
        return DocPageSize;
    }

    public void setDocPageSize(int docPageSize) {
        DocPageSize = docPageSize;
    }

    @Override
    public String toString() {
        return "FileDoc{" +
                "DocToContent='" + DocToContent + '\'' +
                ", DocToContentSize=" + DocToContentSize +
                ", FilePath='" + FilePath + '\'' +
                ", FileName='" + FileName + '\'' +
                ", Flag=" + Flag +
                ", ISDoc=" + ISDoc +
                ", FileTimestamp='" + FileTimestamp + '\'' +
                ", PdfPageSize=" + PdfPageSize +
                ", DocPageSize=" + DocPageSize +
                '}';
    }

}
