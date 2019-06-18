package org.renhj.blog.pojo.dto;

import lombok.Data;

@Data
public class Page {
    private Integer current = 1;
    private Integer size = 10;
    private Object  records;
    private Integer total;
    private Integer pages;

}
