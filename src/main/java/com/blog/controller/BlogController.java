package com.blog.controller;

import com.blog.dto.blog.AddBlogDTO;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.dto.blog.UpdateBlogDTO;
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
    
    @GetMapping("/count")
    @NoAuthorization
    public int count() {
        return blogService.countBlog();
    }

    @PostMapping("/add")
    public String add(@RequestBody @Valid AddBlogDTO addBlogDTO) {
        blogService.add(addBlogDTO);
        return "";
    }

    @PostMapping("/update")
    public String update(@RequestBody @Valid UpdateBlogDTO updateBlogDTO) {
        blogService.updateBlog(updateBlogDTO);
        return "";
    }
    
    @GetMapping("/remove/{blogId}")
    public String remove(@PathVariable("blogId") Integer blogId) {
        blogService.removeById(blogId);
        return "";
    }

    @PostMapping("/page")
    @NoAuthorization
    public BlogDTO page(@RequestBody @Valid GetBlogByPageDTO getBlogByPageDTO) {
        return blogService.getPage(getBlogByPageDTO);
    }
}
