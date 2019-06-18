package org.renhj.blog.service;

import org.renhj.blog.common.BaseService;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.vo.BlogsVo;

import java.util.List;

public interface BlogService {
    /**
     * 根据用户名分页查找所有博客
     * @param username 用户名
     * @param current 当前页
     * @param size 页大小
     * @return 用户所有的博客
     */
    List<BlogsVo> findPostsByUsername(String username, Integer current, Integer size);
}
