package com.example.fa_blog_study.controller;

import com.example.fa_blog_study.service.CategoryService;
import com.example.fa_blog_study.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Result categorys(){
        return categoryService.findAll();
    }

    @GetMapping("/detail")
    public Result detailCategorys(){
        return categoryService.findAll();
    }

    @GetMapping("/detail/{id}")
    public Result detailCategoryById(@PathVariable("id") Long id){
        return categoryService.findDetailCategoryById(id);
    }

}
