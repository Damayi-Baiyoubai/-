package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.dao.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {

    @Select("SELECT * FROM ms_comment WHERE article_id=#{articleId} AND level=#{level} ORDER BY create_date DESC")
    List<Comment> selectCommentsByArticleIdLevel(@Param("articleId") Long articleId,@Param("level") int level);

    @Select("SELECT * FROM ms_comment WHERE parent_id=#{parentId} AND level=#{level} ORDER BY create_date DESC")
    List<Comment> selectCommentsByParentIdLevel(@Param("parentId") Long parentId,@Param("level") int level);

    @Insert("INSERT INTO ms_comment (content,create_date,article_id,author_id,parent_id,to_uid,level) " +
            "VALUES (#{content},#{createDate},#{articleId},#{authorId},#{parentId},#{toUid},#{level})")
    Integer insert(Comment comment);
}
