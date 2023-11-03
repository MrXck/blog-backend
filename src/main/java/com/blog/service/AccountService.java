package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.account.AccountDTO;
import com.blog.dto.account.RegisterAccountDTO;
import com.blog.pojo.Account;

public interface AccountService extends IService<Account> {
    AccountDTO login(AccountDTO accountDTO);

    void register(RegisterAccountDTO registerAccountDTO);
}