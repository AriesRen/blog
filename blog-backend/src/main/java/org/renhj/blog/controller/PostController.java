package org.renhj.blog.controller;


import javafx.print.PageOrientation;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.service.CategoryService;
import org.renhj.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result getAll(@RequestParam(required = false) PostEntity post,
                         @RequestParam(value = "current", required = false, defaultValue = "1")Integer current,
                         @RequestParam(value = "size", required = false, defaultValue = "24")Integer size){
        return Result.ok(postService.findPageByWhere(post, current, size));
    }

    @PostMapping
    public Result postOne(@RequestBody PostEntity post) {
        return Result.ok(postService.save(post));
    }

    @DeleteMapping
    @RequiresUser
    public Result deleteAll(@RequestParam Long[] ids){
        return Result.ok(postService.deleteByIds(PostEntity.class, "postId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id){
        return Result.ok(postService.findById(id));
    }

    @GetMapping("/{id}/next")
    public Result getNext(@PathVariable Long id) {
        return Result.ok(postService.findNext(id));
    }

    @GetMapping("/{id}/previous")
    public Result getPrevious(@PathVariable Long id){
        return Result.ok(postService.findPrevious(id));
    }

    @GetMapping("/{id}/tags")
    public Result getPostTags(@PathVariable Long id) {
        return Result.ok(postService.findTagsByPostId(id));
    }

    @PutMapping("/{id}")
    public Result putOne(@PathVariable Long id, @RequestBody PostEntity post){
        post.setPostId(id);
        return Result.ok(postService.update(post));
    }

    @PatchMapping("/{id}")
    public Result patchOne(@PathVariable Long id, @RequestBody PostEntity post){
        post.setPostId(id);
        return Result.ok(postService.updateSelective(post));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id) {
        return Result.ok(postService.deleteById(id));
    }

}
