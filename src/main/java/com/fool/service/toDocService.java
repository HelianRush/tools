package com.fool.service;

import com.fool.entity.FileDoc;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface toDocService {
    FileDoc readDoc(MultipartFile pdfFile, String toType, Model model)throws IOException;
}
