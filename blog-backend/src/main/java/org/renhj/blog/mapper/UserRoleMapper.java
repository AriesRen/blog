package org.renhj.blog.mapper;


import org.renhj.blog.common.BaseMapper;
import org.renhj.blog.pojo.entity.RoleEntity;
import org.renhj.blog.pojo.entity.UserRoleEntity;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
    List<RoleEntity> selectRolesByUserId(Long userId);
}
