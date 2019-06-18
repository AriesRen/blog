package org.renhj.blog.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.renhj.blog.common.BaseEntity;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "menu")
@Entity
@ToString(callSuper = true)
public class MenuEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String name;
    private String path;
    private String component;
    private String title;
    private String permission;
}
