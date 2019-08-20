package com.fool.service;

import com.fool.entity.Ocr;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface toOcrService {

    Ocr readImages(MultipartFile images, String toType, Model model) throws Exception ;
}
