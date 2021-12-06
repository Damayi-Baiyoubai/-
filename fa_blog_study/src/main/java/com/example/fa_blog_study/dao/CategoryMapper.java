package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.dao.pojo.Category;
import com.example.fa_blog_study.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM ms_category WHERE id=#{categoryId}")
    Category selectCategoryById(Long categoryId);

    @Select("SELECT * FROM ms_category")
    List<Category> selectCategorys();
}
