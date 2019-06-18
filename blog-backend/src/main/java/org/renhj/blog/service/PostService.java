package org.renhj.blog.service;

import org.renhj.blog.common.BaseService;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.TagEntity;
import org.renhj.blog.pojo.vo.BlogsVo;

import java.util.List;

public interface PostService extends BaseService<PostEntity> {
    List<TagEntity> findTagsByPostId(Long id);

    PostEntity findNext(Long id);

    PostEntity findPrevious(Long id);
}
