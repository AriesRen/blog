package org.renhj.blog.controller;


import org.apache.shiro.authz.annotation.RequiresGuest;
import org.renhj.blog.pojo.dto.Result;
import org.renhj.blog.pojo.entity.RoleEntity;
import org.renhj.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result getAll() {
        return Result.ok(roleService.findAll());
    }

    @PostMapping
    public Result postOne(@RequestBody RoleEntity role) {
        return Result.ok(roleService.save(role));
    }

    @DeleteMapping
    public Result deleteIds(@RequestParam Long[] ids) {
        return Result.ok(roleService.deleteByIds(RoleEntity.class, "roleId", Arrays.asList(ids)));
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable Long id) {
        return Result.ok(roleService.findById(id));
    }

    @PutMapping("/{id}")
    public Result putOne(@PathVariable Long id, @RequestBody RoleEntity role) {
        role.setRoleId(id);
        return Result.ok(roleService.update(role));
    }

    @PatchMapping("/{id}")
    public Result patchOne(@PathVariable Long id, @RequestBody RoleEntity role) {
        role.setRoleId(id);
        return Result.ok(roleService.updateSelective(role));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id){
        return Result.ok(roleService.deleteById(id));
    }
}
