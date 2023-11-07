package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.admin.AdminDTO;
import com.blog.mapper.AdminMapper;
import com.blog.pojo.Admin;
import com.blog.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public AdminDTO info() {
        Admin admin = this.list().get(0);

        admin.setPassword("");
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdmin(admin);
        return adminDTO;
    }
}