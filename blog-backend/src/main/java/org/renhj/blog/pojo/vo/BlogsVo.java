package org.renhj.blog.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.renhj.blog.pojo.entity.CategoryEntity;
import org.renhj.blog.pojo.entity.TagEntity;
import org.renhj.blog.pojo.entity.UserEntity;

import java.util.Date;
import java.util.List;

@Data
public class BlogsVo {
    private Long postId;
    private UserEntity user;
    private CategoryEntity category;
    private String postTitle;
    private String postAvatar;
    private String postAuthor;
    private String postImages;
    private String postContent;
    private String postDesc;
    private Date createdTime;
    private Date updatedTime;
    private String createdUser;
    private String updatedUser;
    private List<TagEntity> tags;

    private Long userId;

    private Long categoryId;

}
