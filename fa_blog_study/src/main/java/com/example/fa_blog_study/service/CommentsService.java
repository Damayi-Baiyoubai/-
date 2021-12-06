package com.example.fa_blog_study.service;

import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.param.CommentParam;

public interface CommentsService {
    Result commentsByArticleId(Long articleId);
    Result comment(CommentParam commentParam,String token);
}
