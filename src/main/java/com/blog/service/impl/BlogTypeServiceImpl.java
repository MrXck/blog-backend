package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.type.AddBlogTypeDTO;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.dto.type.UpdateBlogTypeDTO;
import com.blog.mapper.BlogTypeMapper;
import com.blog.pojo.BlogType;
import com.blog.service.BlogTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public void add(AddBlogTypeDTO addBlogTypeDTO) {
        BlogType blogType = new BlogType();
        BeanUtils.copyProperties(addBlogTypeDTO, blogType);
        blogType.setCreateTime(LocalDateTime.now());
        blogType.setUpdateTime(LocalDateTime.now());
        this.save(blogType);
    }

    @Override
    public void updateBlogType(UpdateBlogTypeDTO updateBlogTypeDTO) {
        LambdaUpdateWrapper<BlogType> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BlogType::getId, updateBlogTypeDTO.getId());
        updateWrapper.set(BlogType::getUpdateTime, LocalDateTime.now());
        updateWrapper.set(BlogType::getName, updateBlogTypeDTO.getName());

        this.update(updateWrapper);
    }
}