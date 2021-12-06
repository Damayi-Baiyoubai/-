package com.example.fa_blog_study.service;

import com.example.fa_blog_study.dao.pojo.Category;
import com.example.fa_blog_study.vo.Result;

public interface CategoryService {
    Category findCategoryById(Long categoryId);

    Result findDetailCategoryById(Long categoryId);

    Result findAll();
}
