package com.fool.entity;

public class Ocr {

    // 转换后的文本内容
    public String ocrToContent;

    // 字数
    public long ocrToContentSize;

    // 文件地址
    public String filePath;

    // 文件名
    public String fileName;

    // 转化结果
    public boolean flag;

    public Ocr() {
    }

    public String getOcrToContent() {
        return ocrToContent;
    }

    public void setOcrToContent(String ocrToContent) {
        this.ocrToContent = ocrToContent;
    }

    public long getOcrToContentSize() {
        return ocrToContentSize;
    }

    public void setOcrToContentSize(long ocrToContentSize) {
        this.ocrToContentSize = ocrToContentSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Ocr{" +
                "ocrToContent='" + ocrToContent + '\'' +
                ", ocrToContentSize=" + ocrToContentSize +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", flag=" + flag +
                '}';
    }
}
