package com.example.fa_blog_study.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleTagMapper {

    @Insert("INSERT INTO ms_article_tag (article_id,tag_id) VALUES (#{articleId},#{tagId})")
    int insert(@Param("articleId") Long articleId,@Param("tagId") Long tagId);

    @Select("SELECT article_id FROM ms_article_tag WHERE tag_id=#{tagId}")
    List<Long> selectArticleIdsByTagId(@Param("tagId") Long tagId);

}
