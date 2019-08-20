package com.fool.service.impl;

import com.fool.entity.FileDoc;
import com.fool.service.toDocService;
import com.fool.sys_common.SystemCode;
import com.fool.sys_tool.PDFTools;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class toDocServiceImpl implements toDocService {

    @Override
    public FileDoc readDoc(MultipartFile pdfFile, String toType, Model model) throws IOException {

        toType = "itextpdf";

        FileDoc fileDoc = new FileDoc();
        File file = PDFTools.CreateTempPdfFile(pdfFile, fileDoc);
        switch (toType) {
            case SystemCode.TO_TOPE_COMMON:
                return PDFTools.toEditDocCommon(file, fileDoc);
            case SystemCode.TO_TOPE_COMPOSE:
                return PDFTools.toEditDocVip(file, fileDoc);
        }
        return fileDoc;
    }

}
