package com.blog.controller;

import com.blog.dto.admin.AdminDTO;
import com.blog.dto.admin.UpdateAdminDTO;
import com.blog.service.AdminService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/login")
    @NoAuthorization
    public AdminDTO login(@RequestBody @Valid AdminDTO adminDTO) {
        return adminService.login(adminDTO);
    }

    @PostMapping("/update")
    public String update(@RequestBody @Valid UpdateAdminDTO updateAdminDTO) {
        adminService.updateAdmin(updateAdminDTO);
        return "";
    }
}
