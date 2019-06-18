package org.renhj.blog.mapper;

import org.junit.Test;
import org.renhj.blog.BlogApplicationTests;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.vo.BlogsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.junit.Assert.*;

public class PostMapperTest extends BlogApplicationTests {
    @Autowired
    private PostMapper postMapper;

    @Test
    public void getBlogs() {
        List<BlogsVo> posts = postMapper.findPostsByUsername("admin");
        System.out.println(posts);
    }
}