package com.example.fa_blog_study.service.impl;

import com.example.fa_blog_study.dao.CommentsMapper;
import com.example.fa_blog_study.dao.pojo.Comment;
import com.example.fa_blog_study.dao.pojo.SysUser;
import com.example.fa_blog_study.service.CommentsService;
import com.example.fa_blog_study.service.LoginService;
import com.example.fa_blog_study.service.SysUserService;
import com.example.fa_blog_study.vo.CommentVo;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.UserVo;
import com.example.fa_blog_study.vo.param.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    LoginService loginService;

    @Override
    public Result commentsByArticleId(Long articleId) {

        List<Comment> comments = commentsMapper.selectCommentsByArticleIdLevel(articleId, 1);
        List<CommentVo> commentVos = this.copyList(comments);
        return Result.success(commentVos);
    }

    @Override
    public Result comment(CommentParam commentParam, String token) {

        Comment comment = new Comment();

        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        comment.setArticleId(commentParam.getArticleId());
        SysUser sysUser = loginService.checkToken(token);
        comment.setAuthorId(sysUser.getId());
        Long parent = commentParam.getParent();
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        comment.setLevel( (parent == null || parent == 0) ? 1 : 2);
        commentsMapper.insert(comment);

        return Result.success(null);
    }


    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment :
                comments) {
            CommentVo copy = this.copy(comment);
            commentVos.add(copy);
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setId(String.valueOf(comment.getId()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatCreateDate = simpleDateFormat.format(new Date(comment.getCreateDate()));
        commentVo.setCreateDate(formatCreateDate);

        UserVo userVoById = sysUserService.getUserVoById(comment.getAuthorId());
        commentVo.setAuthor(userVoById);

        Integer level = comment.getLevel();
        if (level > 1) {
            UserVo toUser = sysUserService.getUserVoById(comment.getToUid());
            commentVo.setToUser(toUser);
        }


        if (level == 1) {
            List<CommentVo> commentVos = commentsByParentId(comment.getId());
            commentVo.setChildrens(commentVos);
        }

        return commentVo;
    }

    public List<CommentVo> commentsByParentId(Long parentId) {
        List<Comment> comments = commentsMapper.selectCommentsByParentIdLevel(parentId, 2);
        List<CommentVo> commentVos = this.copyList(comments);
        return commentVos;
    }
}
