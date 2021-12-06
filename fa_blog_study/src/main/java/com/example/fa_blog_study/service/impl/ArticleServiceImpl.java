package com.example.fa_blog_study.service.impl;

import com.example.fa_blog_study.dao.ArticleBodyMapper;
import com.example.fa_blog_study.dao.ArticleMapper;
import com.example.fa_blog_study.dao.ArticleTagMapper;
import com.example.fa_blog_study.dao.pojo.*;
import com.example.fa_blog_study.service.*;
import com.example.fa_blog_study.vo.*;
import com.example.fa_blog_study.vo.param.ArticleParam;
import com.example.fa_blog_study.vo.param.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagService tagService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ArticleBodyMapper articleBodyMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ThreadService threadService;
    @Autowired
    LoginService loginService;
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public Result listArticle(PageParams pageParams) {
        List<Long> articleIds = new ArrayList<>();
        if (pageParams.getTagId() != null) {
            articleIds = articleTagMapper.selectArticleIdsByTagId(pageParams.getTagId());
        }

        List<Article> articles = articleMapper.selectPage((pageParams.getPage() - 1) * pageParams.getPageSize(),
                pageParams.getPageSize(), "weight", "create_date",pageParams,articleIds);

        if (articles != null && articles.size() > 0) {
            return Result.success(this.copyList(articles,true,true));
        } else {
            return Result.success(null);
//            return Result.fail(400, "查找失败");
        }

    }

    @Override
    public Result hots(int limit) {
        List<Article> hotsArticles = articleMapper.getHotsArticles(limit);
        List<ArticleVo> articleVos = this.copyList(hotsArticles,false,false);
        return Result.success(articleVos);
    }

    @Override
    public Result newArticles(int limit) {
        List<Article> newArticles = articleMapper.getNewArticles(limit);
        List<ArticleVo> articleVos = this.copyList(newArticles,false,false);
        return Result.success(articleVos);
    }

    @Override
    public Result listArchives() {
        return Result.success(articleMapper.listArchives());
    }


    @Override
    public Result findArticleById(Long articleId) {
        ArticleVo articleVo = new ArticleVo();
        Article article = articleMapper.selectArticleById(articleId);
        ArticleVo copy = this.copy(article, true, true, true, true);
        threadService.updateArticleViewCount(articleMapper,article);
        return Result.success(copy);
    }

    @Override
    public Result publish(ArticleParam articleParam, String token) {
        Article article = new Article();
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setViewCounts(0);
        SysUser sysUser = loginService.checkToken(token);
        article.setAuthorId(sysUser.getId());

        article.setCategoryId(Long.valueOf(articleParam.getCategory().getId()));
        article.setWeight(Article.Article_Common);
        article.setCreateDate(System.currentTimeMillis());
        articleMapper.insert(article);


        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getId());
        articleMapper.updateArticleBodyId(article);

        for (TagVo tagVo:
             articleParam.getTags()) {
            Long id = Long.valueOf(tagVo.getId());
            articleTagMapper.insert(article.getId(),id);
        }

        Map<String, String> map = new HashMap<>();
        map.put("id",String.valueOf(article.getId()));
        return Result.success(map);
    }

    private List<ArticleVo> copyList(List<Article> articles,boolean needTag,boolean needAuthor) {
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Article article :
                articles) {
            articleVos.add(this.copy(article, needTag, needAuthor,false,false));
        }
        return articleVos;
    }

    private List<ArticleVo> copyList(List<Article> articles,boolean needTag,boolean needAuthor,
                          boolean needBody,boolean needCategory) {
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Article article :
                articles) {
            articleVos.add(this.copy(article, needTag, needAuthor,needBody,needCategory));
        }
        return articleVos;
    }

    private ArticleVo copy(Article article, boolean needTag, boolean needAuthor,
                           boolean needBody,boolean needCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setId(String.valueOf(article.getId()));
        if (article.getCreateDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String formatTime = simpleDateFormat.format(new Date(article.getCreateDate()));
            articleVo.setCreateDate(formatTime);
        }
        if (needTag) {
            List<TagVo> tagsByArticleId = tagService.getTagsByArticleId(article.getId());
            articleVo.setTags(tagsByArticleId);
        }
        if (needAuthor) {
            SysUser userById = sysUserService.getUserById(article.getId());
            articleVo.setAuthor(userById.getNickname());

        }
        if (needBody) {
            ArticleBody articleBodyById = this.findArticleBodyById(article.getBodyId());
            ArticleBodyVo articleBodyVo = new ArticleBodyVo();
            articleBodyVo.setContent(articleBodyById.getContent());
            articleVo.setBody(articleBodyVo);
        }
        if (needCategory) {
            Category categoryById = categoryService.findCategoryById(article.getCategoryId());
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(categoryById,categoryVo);
            categoryVo.setId(String.valueOf(categoryById.getId()));
            articleVo.setCategory(categoryVo);
        }

        return articleVo;
    }



    private ArticleBody findArticleBodyById(Long bodyId) {
        return articleBodyMapper.selectAticleBodyById(bodyId);
    }
}
