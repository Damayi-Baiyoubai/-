package com.example.fa_blog_study.service;

import com.example.fa_blog_study.dao.ArticleMapper;
import com.example.fa_blog_study.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {


    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article){

//        Integer viewCounts = article.getViewCounts();
//        Article updateArticle = new Article();
//        updateArticle.setViewCounts(viewCounts + 1);
        articleMapper.updateArticleViewCount(article);
    }
}
