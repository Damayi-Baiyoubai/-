package com.example.fa_blog_study.controller;

import com.example.fa_blog_study.vo.Result;
import io.netty.util.internal.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    public Result upload(@RequestParam("image")MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtil.substringAfter(originalFilename,'.');
        return null;
    }
}
