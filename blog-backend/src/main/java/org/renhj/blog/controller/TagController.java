package org.renhj.blog.controller;


import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.renhj.blog.mapper.PostTagMapper;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.PostTagEntity;
import org.renhj.blog.pojo.entity.TagEntity;
import org.renhj.blog.service.PostService;
import org.renhj.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private PostService postService;


    @GetMapping
    public Result getAll(){
        return Result.ok(tagService.findAll());
    }

    @PostMapping
    @RequiresUser
    public Result postOne(@RequestBody TagEntity tag) {
        return Result.ok(tagService.save(tag));
    }

    @DeleteMapping
    public Result deleteIds(@RequestParam Long[] ids) {
        return Result.ok(tagService.deleteByIds(TagEntity.class, "tagId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id) {
        return Result.ok(tagService.findById(id));
    }

    @GetMapping("/{id}/post")
    public Result getPostByTag(@PathVariable("id")Long tagId){
      return Result.ok(tagService.selectPostByTagId(tagId));
    }

    @PutMapping("/{id}")
    public Result putOne(@PathVariable Long id, @RequestBody TagEntity tag) {
        tag.setTagId(id);
        return Result.ok(tagService.update(tag));
    }

    @PatchMapping("/{id}")
    public Result patchOne(@PathVariable Long id, @RequestBody TagEntity tag) {
        tag.setTagId(id);
        return Result.ok(tagService.updateSelective(tag));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id){
        return Result.ok(tagService.deleteById(id));
    }
}
