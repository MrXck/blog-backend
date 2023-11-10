package com.blog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer typeId;
    private Boolean isShow;
    private String image;
}
