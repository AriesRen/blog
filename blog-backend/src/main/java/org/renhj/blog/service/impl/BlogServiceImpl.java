package org.renhj.blog.service.impl;

import org.renhj.blog.mapper.PostMapper;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.vo.BlogsVo;
import org.renhj.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final PostMapper postMapper;

    @Autowired
    public BlogServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<BlogsVo> findPostsByUsername(String username, Integer current, Integer size) {
        return postMapper.findPostsByUsername(username);
    }
}
