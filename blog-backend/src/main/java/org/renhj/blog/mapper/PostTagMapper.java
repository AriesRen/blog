package org.renhj.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.renhj.blog.common.BaseMapper;
import org.renhj.blog.pojo.entity.PostTagEntity;

import java.util.List;


public interface PostTagMapper extends BaseMapper<PostTagEntity> {
    List<PostTagEntity> selectByPostId(Long postId);

    List<PostTagEntity> selectByTagId(Long tagId);

    Integer selectTotalByPostId(Long postId);

    List<PostTagEntity> selectByPostIdWithPage(@Param("postId") Long postId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer selectTotalByTagId(Long tagId);

    List<PostTagEntity> selectByTagIdWithPage(@Param("tagId") Long postId, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
