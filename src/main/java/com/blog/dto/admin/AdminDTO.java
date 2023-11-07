package com.blog.dto.admin;

import com.blog.pojo.Admin;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminDTO {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private Admin admin;

    private String token;
}
