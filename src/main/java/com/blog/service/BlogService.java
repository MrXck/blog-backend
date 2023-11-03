package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.pojo.Blog;

public interface BlogService extends IService<Blog> {
    BlogDTO getByPage(GetBlogByPageDTO getBlogByPageDTO);
}