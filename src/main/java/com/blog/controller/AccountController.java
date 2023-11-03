package com.blog.controller;

import com.blog.dto.account.AccountDTO;
import com.blog.dto.account.RegisterAccountDTO;
import com.blog.service.AccountService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @NoAuthorization
    public AccountDTO login(@RequestBody @Valid AccountDTO accountDTO) {
        return accountService.login(accountDTO);
    }

    @PostMapping("/register")
    @NoAuthorization
    public String register(@RequestBody @Valid RegisterAccountDTO registerAccountDTO) {
        accountService.register(registerAccountDTO);
        return "";
    }
}
