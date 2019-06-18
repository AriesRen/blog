package org.renhj.blog.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.renhj.blog.mapper.UserMapper;
import org.renhj.blog.pojo.entity.UserEntity;
import org.renhj.blog.pojo.vo.LoginVo;
import org.renhj.blog.pojo.vo.RegisterVo;
import org.renhj.blog.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Object login(LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword());
        subject.login(token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", subject.getSession().getId());
        return map;
    }

    @Override
    public UserEntity register(RegisterVo registerVo) {

        return null;
    }
}
