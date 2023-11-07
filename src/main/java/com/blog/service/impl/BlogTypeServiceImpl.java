package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.mapper.BlogTypeMapper;
import com.blog.pojo.BlogType;
import com.blog.service.BlogTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogTypeServiceImpl extends ServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {

    @Override
    public BlogTypeDTO all() {
        List blogTypes = this.baseMapper.getAllBlogTypeWithCount();
        BlogTypeDTO blogTypeDTO = new BlogTypeDTO();
        blogTypeDTO.setBlogTypes(blogTypes);
        return blogTypeDTO;
    }
}