package org.renhj.blog.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.renhj.blog.common.BaseEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "post")
@Entity
@ToString(callSuper = true)
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private Long userId;
    private String postTitle;
    private String postContent;
    private String postAuthor;
    private String postAvatar;
    private String postImages;
    private String postDesc;
    private byte postStatus;
    private Long categoryId;
}
