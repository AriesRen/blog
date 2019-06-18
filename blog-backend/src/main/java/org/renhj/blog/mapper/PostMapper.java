package org.renhj.blog.mapper;

import org.renhj.blog.common.BaseMapper;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.vo.BlogsVo;

import java.util.List;


public interface PostMapper extends BaseMapper<PostEntity> {
    List<BlogsVo> findPostsByUsername(String username);

    PostEntity selectNext(Long id);

    PostEntity selectPrevious(Long id);
}
