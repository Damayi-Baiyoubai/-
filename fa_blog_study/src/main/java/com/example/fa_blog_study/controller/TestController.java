package com.example.fa_blog_study.controller;

import com.example.fa_blog_study.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    public Result test(){

        return Result.success(null);
    }
}
