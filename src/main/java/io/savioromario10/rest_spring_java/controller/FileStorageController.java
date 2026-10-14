package io.savioromario10.rest_spring_java.controller;

import io.savioromario10.rest_spring_java.data.dto.v1.UploadFileResponseDTO;

import io.savioromario10.rest_spring_java.service.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File endpoint")
@RestController
@RequestMapping("/api/v1/file")
public class FileStorageController {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);

    @Autowired
    private FileStorageService service;

    public UploadFileResponseDTO uploadFile(MultipartFile file){
        return null;
    }

    public List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] file){
        return null;
    }

    public ResponseEntity<ResponseEntity> downloadFile(String FileName, HttpServletRequest request){
        return null;
    }
}