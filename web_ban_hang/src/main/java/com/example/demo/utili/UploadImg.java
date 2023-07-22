package com.example.demo.utili;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadImg {
    public String uploadImg(MultipartFile file) {
        String path = "D:\\TAI_LIEU_HOC_TAP\\JAVA_6\\web_ban_hang\\src\\main\\resources\\static\\image";
        File myFile = new File(path);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        File saveFile = null;
        try {
            saveFile = new File(myFile, file.getOriginalFilename());
            file.transferTo(saveFile);
            return "Upload image thành công!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload image thất bại!";
        }
    }
}
