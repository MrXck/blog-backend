package com.blog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogType {
    private Integer id;
    private String name;
    private Integer num;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
