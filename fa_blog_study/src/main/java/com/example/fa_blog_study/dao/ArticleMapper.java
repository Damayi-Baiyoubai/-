package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.dao.dos.Archives;
import com.example.fa_blog_study.dao.pojo.Article;
import com.example.fa_blog_study.vo.param.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface ArticleMapper {

//    @Select("SELECT * FROM ms_article ORDER BY ${order1} DESC,${order2} DESC" +
//            " LIMIT #{offset},#{rows}")
    public List<Article> selectPage(@Param("offset")int offset,@Param("rows")int rows,
                                    @Param("order1")String order1,
                                    @Param("order2")String order2,
                                    @Param("pageParams")PageParams pageParams,
                                    @Param("articleIds")List<Long> articleIds);

    @Select("SELECT id,title FROM ms_article ORDER BY view_counts DESC LIMIT #{limit}")
    List<Article> getHotsArticles(int limit);

    @Select("SELECT id,title FROM ms_article ORDER BY create_date DESC LIMIT #{limit}")
    List<Article> getNewArticles(int limit);

    @Select("SELECT YEAR(from_unixtime(create_date/1000)) As year," +
            "MONTH(from_unixtime(create_date/1000)) AS month,COUNT(*) AS count FROM ms_article GROUP BY year,month " +
            "ORDER BY year DESC,month DESC;")
    List<Archives> listArchives();

    @Select("SELECT * FROM ms_article WHERE id=#{articleId}")
    Article selectArticleById(Long articleId);

    @Update("UPDATE ms_article SET view_counts=view_counts+1 WHERE id=#{id}")
    int updateArticleViewCount(Article updateArticle);

    int insert(Article article);

    @Update("UPDATE ms_article SET body_id=#{bodyId} WHERE id=#{id}")
    int updateArticleBodyId(Article updateArticle);


}
