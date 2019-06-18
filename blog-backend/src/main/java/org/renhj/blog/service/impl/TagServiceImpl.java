package org.renhj.blog.service.impl;

import org.renhj.blog.common.BaseServiceImpl;
import org.renhj.blog.mapper.PostMapper;
import org.renhj.blog.mapper.PostTagMapper;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.PostTagEntity;
import org.renhj.blog.pojo.entity.TagEntity;
import org.renhj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends BaseServiceImpl<TagEntity> implements TagService {
    @Autowired
    private PostTagMapper postTagMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostEntity> selectPostByTagId(Long tagId) {
        List<PostTagEntity> postTagEntities = postTagMapper.selectByTagId(tagId);
        List<PostEntity> posts = new ArrayList<>();
        for (PostTagEntity postTagEntity : postTagEntities) {
            posts.add(postMapper.selectByPrimaryKey(postTagEntity.getPostId()));
        }
        return posts;
    }
}
