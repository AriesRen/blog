package org.renhj.blog.common;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {
    private Date createdTime;
    private String createdUser;
    private Date updatedTime;
    private String updatedUser;
}
