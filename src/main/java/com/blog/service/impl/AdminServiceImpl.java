package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.admin.AdminDTO;
import com.blog.dto.admin.UpdateAdminDTO;
import com.blog.exception.APIException;
import com.blog.mapper.AdminMapper;
import com.blog.pojo.Admin;
import com.blog.service.AdminService;
import com.blog.utils.Constant;
import com.blog.utils.JwtUtils;
import com.blog.utils.MD5Utils;
import com.blog.utils.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AdminDTO info() {
        Admin admin = this.list().get(0);

        admin.setPassword("");
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdmin(admin);
        return adminDTO;
    }

    @Override
    public AdminDTO login(AdminDTO adminDTO) {
        String username = adminDTO.getUsername();
        String password = adminDTO.getPassword();

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        queryWrapper.eq(Admin::getPassword, MD5Utils.md5(password));

        Admin admin = this.getOne(queryWrapper);

        if (admin == null) {
            throw new APIException(Constant.LOGIN_ERROR);
        }

        this.updateById(admin);

        Map<String, Object> claims = new HashMap<>(16);
        claims.put("userId", admin.getId());
        String token = jwtUtils.createToken(claims, 720);
        admin.setPassword(null);
        adminDTO.setToken(token);
        adminDTO.setPassword("");
        adminDTO.setAdmin(admin);
        return adminDTO;
    }

    @Override
    public void updateAdmin(UpdateAdminDTO updateAdminDTO) {
        String username = updateAdminDTO.getUsername();
        String password = updateAdminDTO.getPassword();

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        queryWrapper.ne(Admin::getId, UserThreadLocal.get());
        Admin user = this.getOne(queryWrapper);
        if (user != null) {
            throw new APIException(Constant.USERNAME_ALREADY_ERROR);
        }

        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getId, UserThreadLocal.get());
        updateWrapper.set(Admin::getUsername, username);
        updateWrapper.set(Admin::getPassword, MD5Utils.md5(password));
        updateWrapper.set(Admin::getUpdateTime, LocalDateTime.now());

        this.update(updateWrapper);
    }
}