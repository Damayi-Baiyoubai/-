package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.dao.pojo.ArticleBody;
import com.example.fa_blog_study.vo.ArticleBodyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleBodyMapper {

    @Select("SELECT * FROM ms_article_body WHERE id=#{bodyId}")
    ArticleBody selectAticleBodyById(Long bodyId);

    int insert(ArticleBody articleBody);
}
