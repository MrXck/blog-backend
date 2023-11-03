package com.blog.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
