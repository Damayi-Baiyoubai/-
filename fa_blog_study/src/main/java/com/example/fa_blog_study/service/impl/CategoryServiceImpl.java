package com.example.fa_blog_study.service.impl;

import com.example.fa_blog_study.dao.CategoryMapper;
import com.example.fa_blog_study.dao.pojo.Category;
import com.example.fa_blog_study.service.CategoryService;
import com.example.fa_blog_study.vo.CategoryVo;
import com.example.fa_blog_study.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public Result findDetailCategoryById(Long categoryId) {
        Category categoryById = this.findCategoryById(categoryId);
        CategoryVo copy = this.copy(categoryById);
        return Result.success(copy);
    }

    @Override
    public Result findAll() {
        List<Category> categories = categoryMapper.selectCategorys();
        List<CategoryVo> categoryVos = this.copyList(categories);
        return Result.success(categoryVos);
    }
    public List<CategoryVo> copyList(List<Category> categorys){
        List<CategoryVo> categoryVos = new ArrayList<>();
        for (Category category:
             categorys) {
            CategoryVo copy = this.copy(category);
            categoryVos.add(copy);
        }
        return categoryVos;
    }

    public CategoryVo copy(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
}
