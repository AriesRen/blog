package org.renhj.blog.mapper;

import org.junit.Test;
import org.renhj.blog.BlogApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRoleMapperTest extends BlogApplicationTests {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    public void userRole(){
        System.out.println(userRoleMapper.selectRolesByUserId(1L));
    }

}