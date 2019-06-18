package org.renhj.blog.controller;

import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.CategoryEntity;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.service.CategoryService;
import org.renhj.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @GetMapping
    public Result getAll(@RequestParam(value = "current", required = false, defaultValue = "1")Integer current,
                         @RequestParam(value = "size", required = false, defaultValue = "10")Integer size,
                         @RequestParam(required = false)CategoryEntity category){
        return Result.ok(categoryService.findPageByWhere(category, current, size));
    }

    @PostMapping
    @RequiresUser
    public Result postOne(@RequestBody CategoryEntity categoryEntity) {
        return Result.ok(categoryService.save(categoryEntity));
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id){
        return Result.ok(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    @RequiresRoles("admin")
    public Result putOne(@PathVariable Long id, @RequestBody CategoryEntity category) {
        category.setCategoryId(id);
        return Result.ok(categoryService.update(category));
    }

    @PatchMapping("/{id}")
    @RequiresUser
    public Result patchOne(@PathVariable Long id, @RequestBody CategoryEntity category){
        category.setCategoryId(id);
        return Result.ok(categoryService.updateSelective(category));
    }

    @GetMapping("/{id}/post")
    public Result getBlogsByCategory(@PathVariable("id")Long categoryId,
                                     @RequestParam(value = "current", required = false, defaultValue = "1")Integer current,
                                     @RequestParam(value = "size", required = false, defaultValue = "24")Integer size){
        PostEntity postEntity = new PostEntity();
        return Result.ok(postService.findPageByWhere(postEntity, current, size));
    }

}
