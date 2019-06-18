package org.renhj.blog.controller;


import org.apache.shiro.authz.annotation.RequiresGuest;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.MenuEntity;
import org.renhj.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    @RequiresGuest
    public Result getAll() {
        return Result.ok(menuService.findAll());
    }

    @PostMapping
    public Result postOne(@RequestBody MenuEntity menu) {
        return Result.ok(menuService.save(menu));
    }

    @DeleteMapping
    public Result deleteAll(@RequestParam Long[] ids) {
        return Result.ok(menuService.deleteByIds(MenuEntity.class, "menuId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    @RequiresGuest
    public Result getOne(@PathVariable Long id) {
        return Result.ok(menuService.findById(id));
    }

    @PutMapping("/{id}")
    public Result putOne(@PathVariable Long id, @RequestBody MenuEntity menu) {
        menu.setMenuId(id);
        return Result.ok(menuService.update(menu));
    }

    @PatchMapping("/{id}")
    public Result patchOne(@PathVariable Long id, @RequestBody MenuEntity menu) {
        menu.setMenuId(id);
        return Result.ok(menuService.updateSelective(menu));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id){
        return Result.ok(menuService.deleteById(id));
    }
}
