package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.admin.AdminDTO;
import com.blog.pojo.Admin;

public interface AdminService extends IService<Admin> {
    AdminDTO info();

    AdminDTO login(AdminDTO adminDTO);
}