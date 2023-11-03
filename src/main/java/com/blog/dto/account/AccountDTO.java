package com.blog.dto.account;

import com.blog.pojo.Account;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountDTO {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    private String token;

    private Account account;
}
