package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.pojo.BlogType;

public interface BlogTypeService extends IService<BlogType> {
    BlogTypeDTO all();
}