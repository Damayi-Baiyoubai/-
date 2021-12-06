package com.example.fa_blog_study.controller;

import com.example.fa_blog_study.service.CommentsService;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);
    }

    @PostMapping("/create/change")
    public Result comment(@RequestBody CommentParam commentParam,@RequestHeader("Authorization") String token){
        return commentsService.comment(commentParam,token);
    }

}
