package com.example.fa_blog_study;

import com.alibaba.fastjson.JSON;
import com.example.fa_blog_study.dao.ArticleMapper;
import com.example.fa_blog_study.dao.TagMapper;
import com.example.fa_blog_study.dao.pojo.Article;
import com.example.fa_blog_study.service.ArticleService;
import com.example.fa_blog_study.utils.JWTUtils;
import com.example.fa_blog_study.vo.ArticleVo;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.TagVo;
import com.example.fa_blog_study.vo.param.PageParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FaBlogStudyApplicationTests {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    TagMapper tagMapper;


    @Test
    void test5(){
        List<TagVo> tagsByArticleId = tagMapper.getTagsByArticleId((long) 1);
        System.out.println(tagsByArticleId.get(0).getId());

    }

    @Test
    void test4(){
        String s = JSON.toJSONString(new PageParams());
        System.out.println(s);
    }

    @Test
    void test3(){
        String token = JWTUtils.createToken((long) 300);
        System.out.println(token);
    }

    @Test
    void test2(){
        String execute = redisTemplate.execute(RedisConnectionCommands::ping);
        System.out.println(execute);
    }

    @Test
    void test1(){

    }

    @Test
    void contextLoads() {
        Result result = articleService.listArticle(new PageParams());
        System.out.println(result);
    }

}
