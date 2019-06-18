package org.renhj.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.service.BlogService;
import org.renhj.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "博客接口", description = "请求用户的博客文章")
@RestController
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;
    private final PostService postService;


    @Autowired
    public BlogController(BlogService blogService, PostService postService) {
        this.blogService = blogService;
        this.postService = postService;
    }


    @GetMapping("/{username}")
    @ApiOperation("通过用户名获取文章")
    public Result getBlogByUserName(
            @PathVariable(value = "username", required = false) String username,
            @RequestParam(value = "current",defaultValue = "1", required = false) Integer current,
            @RequestParam(value = "size", defaultValue = "5", required = false)Integer size){
        return Result.ok(blogService.findPostsByUsername(username, current, size));
    }


    @GetMapping
    @ApiOperation("查找所有博客文章")
    public Result getBlogs(@RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                           @RequestParam(value = "size", required = false, defaultValue = "24") Integer size
    ){
        PostEntity postEntity = new PostEntity();
        return Result.ok(postService.findPageByWhere(postEntity, current, size));
    }

}
