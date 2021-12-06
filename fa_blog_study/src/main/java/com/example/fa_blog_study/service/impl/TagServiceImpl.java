package com.example.fa_blog_study.service.impl;

import com.example.fa_blog_study.dao.TagMapper;
import com.example.fa_blog_study.service.TagService;
import com.example.fa_blog_study.vo.Result;
import com.example.fa_blog_study.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<TagVo> getTagsByArticleId(Long articleId) {
        List<TagVo> tagsByArticleId = tagMapper.getTagsByArticleId(articleId);

        return tagsByArticleId;
    }

    @Override
    public Result hots(int limit) {
        List<Long> hotsTagIds = tagMapper.getHotsTagIds(limit);
        if(CollectionUtils.isEmpty(hotsTagIds)){
            return Result.success(Collections.EMPTY_LIST);
        }
        List<TagVo> tagsByTagIds = tagMapper.getTagsByTagIds(hotsTagIds);

        return Result.success(tagsByTagIds);
    }

    @Override
    public Result findAll() {
        List<TagVo> tagVos = tagMapper.selectTags();
        return Result.success(tagVos);
    }

    @Override
    public Result findDetailById(Long id) {
        List<Long> longs = new ArrayList<>();
        longs.add(id);
        List<TagVo> tagsByTagIds = tagMapper.getTagsByTagIds(longs);
        return Result.success(tagsByTagIds.get(0));
    }


}
