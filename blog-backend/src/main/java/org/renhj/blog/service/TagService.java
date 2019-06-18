package org.renhj.blog.service;

import org.renhj.blog.common.BaseService;
import org.renhj.blog.pojo.entity.PostEntity;
import org.renhj.blog.pojo.entity.TagEntity;

import java.util.List;

public interface TagService extends BaseService<TagEntity> {
    List<PostEntity> selectPostByTagId(Long tagId);
}
