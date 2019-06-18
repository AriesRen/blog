package org.renhj.blog.service;

import org.renhj.blog.pojo.entity.UserEntity;
import org.renhj.blog.pojo.vo.LoginVo;
import org.renhj.blog.pojo.vo.RegisterVo;

public interface AuthService {

    Object login(LoginVo loginVo);

    UserEntity register(RegisterVo registerVo);

}
