package com.example.fa_blog_study.handler;

import com.example.fa_blog_study.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception e){

        e.printStackTrace();

        return Result.fail(-999,"系统异常");
    }

}
