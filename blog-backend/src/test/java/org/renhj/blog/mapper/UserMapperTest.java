package org.renhj.blog.mapper;

import org.junit.Test;
import org.renhj.blog.BlogApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserMapperTest extends BlogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        System.out.println(userMapper.selectOneByPhone("18435175817"));
    }

}