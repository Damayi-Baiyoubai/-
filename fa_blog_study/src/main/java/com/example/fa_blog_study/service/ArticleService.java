package com.example.fa_blog_study.service;

import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.param.ArticleParam;
import com.example.fa_blog_study.vo.param.PageParams;

public interface ArticleService {
    Result listArticle(PageParams pageParams);
    Result hots(int limit);
    Result newArticles(int limit);
    Result listArchives();

    Result findArticleById(Long articleId);

    Result publish(ArticleParam articleParam, String token);
}
