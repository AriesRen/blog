package org.renhj.blog.mapper;

import org.renhj.blog.common.BaseMapper;
import org.renhj.blog.pojo.entity.UserEntity;


public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity selectOneByUsername(String username);

    UserEntity selectOneByPhone(String phone);

    UserEntity selectOneByEmail(String email);
}
