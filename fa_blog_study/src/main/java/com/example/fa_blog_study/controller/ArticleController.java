package com.example.fa_blog_study.controller;

import com.example.fa_blog_study.common.aop.LogAnnotation;
import com.example.fa_blog_study.common.cache.Cache;
import com.example.fa_blog_study.service.ArticleService;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.param.ArticleParam;
import com.example.fa_blog_study.vo.param.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Cache
    @LogAnnotation(module = "articles",operator = "获得所有文章")
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listArticle(pageParams);
    }

    @PostMapping("/hot")
    public Result hotArticles(){
        int limit= 5;
        return articleService.hots(limit);
    }

    @PostMapping("/new")
    public Result newArticles(){
        int limit= 5;
        return articleService.newArticles(limit);
    }

    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){

        return articleService.findArticleById(articleId);
    }

    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParam articleParam,@RequestHeader("Authorization") String token){

        return articleService.publish(articleParam,token);
    }

}
