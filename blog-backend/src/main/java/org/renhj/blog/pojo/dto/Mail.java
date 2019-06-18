package org.renhj.blog.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Mail {
    private String from;
    private String to;
    private String message;
    private String subject;
    private List<String> attachment;
}
