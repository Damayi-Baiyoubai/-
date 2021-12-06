package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {
    @Select("SELECT * FROM ms_tag WHERE id IN (SELECT tag_id FROM ms_article_tag WHERE article_id=#{articleId})")
    List<TagVo> getTagsByArticleId(Long articleId);

    @Select("SELECT tag_id FROM ms_article_tag GROUP BY tag_id " +
            "ORDER BY COUNT(article_id) DESC LIMIT #{limit}")
    List<Long> getHotsTagIds(int limit);

    List<TagVo> getTagsByTagIds(@Param("articleId") List<Long> articleId);
    @Select("SELECT * FROM ms_tag")
    List<TagVo> selectTags();
}
