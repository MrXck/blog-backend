package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.account.AccountDTO;
import com.blog.dto.account.RegisterAccountDTO;
import com.blog.exception.APIException;
import com.blog.mapper.AccountMapper;
import com.blog.pojo.Account;
import com.blog.service.AccountService;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import com.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AccountDTO login(AccountDTO accountDTO) {
        String username = accountDTO.getUsername();
        String password = accountDTO.getPassword();

        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUsername, username);
        queryWrapper.eq(Account::getPassword, MD5Utils.md5(password));

        Account user = this.getOne(queryWrapper);

        if (user == null) {
            throw new APIException(Constant.LOGIN_ERROR);
        }

        Map<String, Object> claims = new HashMap<>(16);
        claims.put("userId", user.getId());
        String token = jwtUtils.createToken(claims, 720);
        user.setPassword(null);
        accountDTO.setToken(token);
        accountDTO.setPassword("");
        accountDTO.setAccount(user);
        return accountDTO;
    }

    @Override
    public void register(RegisterAccountDTO registerAccountDTO) {
        String username = registerAccountDTO.getUsername();
        String password = registerAccountDTO.getPassword();

        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Account::getUsername, username);
        Account user = this.getOne(queryWrapper);

        if (user != null) {
            throw new APIException(Constant.USERNAME_ALREADY_ERROR);
        }

        user = new Account();
        user.setUsername(username);
        user.setPassword(MD5Utils.md5(password));
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        this.save(user);
    }
}