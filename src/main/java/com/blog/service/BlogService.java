package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.archives.ArchivesDTO;
import com.blog.dto.archives.GetArchivesDTO;
import com.blog.dto.blog.AddBlogDTO;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.pojo.Blog;

import java.util.List;

public interface BlogService extends IService<Blog> {
    BlogDTO getByPage(GetBlogByPageDTO getBlogByPageDTO);

    ArchivesDTO getArchivesByTime(GetArchivesDTO getArchivesDTO);

    List all();

    void add(AddBlogDTO addBlogDTO);
}