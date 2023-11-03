package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.pojo.BlogType;
import com.blog.mapper.BlogTypeMapper;
import com.blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {
}