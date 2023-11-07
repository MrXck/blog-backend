package com.blog.controller;

import com.blog.dto.admin.AdminDTO;
import com.blog.service.AdminService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/info")
    @NoAuthorization
    public AdminDTO info() {
        return adminService.info();
    }
}
