package org.renhj.blog.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.UserEntity;
import org.renhj.blog.pojo.enums.ResultCode;
import org.renhj.blog.service.PostService;
import org.renhj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    @RequiresRoles("admin")
    public Result getAll() {
        return Result.ok(userService.findAll());
    }

    @GetMapping("/{id}/post")
    public Result getPostByUser(@PathVariable("id") Long userId,
                               @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                               @RequestParam(value = "size", required = false, defaultValue = "24") Integer size) {
        UserEntity user = userService.findById(userId);
        PostEntity post = new PostEntity();
        post.setUserId(user.getUserId());
        return Result.ok(postService.findPageByWhere(post, current, size));
    }

    @PostMapping
    public Result postOne(@RequestBody UserEntity userEntity) {
        return Result.ok(userService.saveSelective(userEntity));
    }

    @DeleteMapping
    @RequiresRoles("admin")
    public Result deleteAll(@RequestParam Long[] ids) {
        return Result.ok(userService.deleteByIds(UserEntity.class, "userId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id) {
        return Result.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    @RequiresUser
    public Result updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        user.setUserId(id);
        return Result.ok(userService.update(user));
    }

    @PatchMapping("/{id}")
    @RequiresUser
    public Result patchUser(@PathVariable Long id, @RequestBody UserEntity user) {
        user.setUserId(id);
        return Result.ok(userService.update(user));
    }

    @DeleteMapping("/{id}")
    @RequiresUser
    public Result deleteUserById(@PathVariable Long id) {
        return Result.ok(userService.deleteById(id));
    }

    @GetMapping("/current")
    public Result getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Result.ok(subject.getPrincipal());
        } else {
            return new Result(ResultCode.UNLOGIN);
        }
    }

}
