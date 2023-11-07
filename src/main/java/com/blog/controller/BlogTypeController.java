package com.blog.controller;

import com.blog.dto.type.BlogTypeDTO;
import com.blog.service.BlogTypeService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogType")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @GetMapping("/all")
    @NoAuthorization
    public BlogTypeDTO all() {
        return blogTypeService.all();
    }
}
