package org.renhj.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.renhj.blog.common.BaseServiceImpl;
import org.renhj.blog.exception.ServiceException;
import org.renhj.blog.mapper.PostMapper;
import org.renhj.blog.mapper.PostTagMapper;
import org.renhj.blog.mapper.TagMapper;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.PostTagEntity;
import org.renhj.blog.pojo.entity.TagEntity;
import org.renhj.blog.pojo.vo.BlogsVo;
import org.renhj.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl extends BaseServiceImpl<PostEntity> implements PostService {

    @Autowired
    private PostTagMapper postTagMapper;

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagEntity> findTagsByPostId(Long id) {
        List<PostTagEntity> postTagEntities = postTagMapper.selectByPostId(id);
        List<TagEntity> tagEntities = new ArrayList<>();
        postTagEntities.forEach(postTagEntity -> {
            tagEntities.add(tagMapper.selectByPrimaryKey(postTagEntity.getTagId()));
        });
        return tagEntities;
    }

    @Override
    public PostEntity findNext(Long id) {
        try {
            log.info("获取 "+id+" 下一篇");
            return postMapper.selectNext(id);
        } catch (Exception e){
            log.info("Service 异常！");
            log.info(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public PostEntity findPrevious(Long id) {
        try {
            log.info("获取 "+id+" 上一篇");
            return postMapper.selectPrevious(id);
        } catch (Exception e){
            log.info("Service 异常！");
            log.info(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
