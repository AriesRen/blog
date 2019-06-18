package org.renhj.blog.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.renhj.blog.common.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String nickname;
    @JsonIgnore
    private String password;
    private String description;
    private String title;
    private String avatar;
    private String blogUrl;
    private String birth;
    private String email;
    private String phone;
    private byte status;
}
