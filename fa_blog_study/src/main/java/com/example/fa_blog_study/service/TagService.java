package com.example.fa_blog_study.service;

import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.TagVo;


import java.util.List;

public interface TagService {
    List<TagVo> getTagsByArticleId(Long articleId);
    Result hots(int limit);

    Result findAll();

    Result findDetailById(Long id);
}
