package com.blog.controller;

import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.service.BlogService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/getByPage")
    @NoAuthorization
    public BlogDTO getByPage(@RequestBody @Valid GetBlogByPageDTO getBlogByPageDTO) {
        return blogService.getByPage(getBlogByPageDTO);
    }

    @GetMapping("/{blogId}")
    @NoAuthorization
    public BlogDTO getById(@PathVariable("blogId") Integer blogId) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlog(blogService.getById(blogId));
        return blogDTO;
    }
}
