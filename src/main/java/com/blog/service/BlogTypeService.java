package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.type.AddBlogTypeDTO;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.dto.type.UpdateBlogTypeDTO;
import com.blog.pojo.BlogType;

public interface BlogTypeService extends IService<BlogType> {
    BlogTypeDTO all();

    void add(AddBlogTypeDTO addBlogTypeDTO);

    void updateBlogType(UpdateBlogTypeDTO updateBlogTypeDTO);
}