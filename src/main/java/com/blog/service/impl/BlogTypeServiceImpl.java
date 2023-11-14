package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.type.AddBlogTypeDTO;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.dto.type.GetTypeByPageDTO;
import com.blog.dto.type.UpdateBlogTypeDTO;
import com.blog.exception.APIException;
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

        LambdaQueryWrapper<BlogType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogType::getName, blogType.getName());
        if (this.count(queryWrapper) != 0) {
            throw new APIException("已经有这个分类了");
        }

        blogType.setCreateTime(LocalDateTime.now());
        blogType.setUpdateTime(LocalDateTime.now());
        this.save(blogType);
    }

    @Override
    public void updateBlogType(UpdateBlogTypeDTO updateBlogTypeDTO) {
        LambdaQueryWrapper<BlogType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogType::getName, updateBlogTypeDTO.getName());
        queryWrapper.ne(BlogType::getId, updateBlogTypeDTO.getId());

        if (this.count(queryWrapper) != 0) {
            throw new APIException("已经有这个分类了");
        }

        LambdaUpdateWrapper<BlogType> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BlogType::getId, updateBlogTypeDTO.getId());
        updateWrapper.set(BlogType::getUpdateTime, LocalDateTime.now());
        updateWrapper.set(BlogType::getName, updateBlogTypeDTO.getName());

        this.update(updateWrapper);
    }

    @Override
    public BlogTypeDTO getByPage(GetTypeByPageDTO getTypeByPageDTO) {
        String keyword = getTypeByPageDTO.getKeyword();
        Page<BlogType> page = new Page<>(getTypeByPageDTO.getPageNum(), getTypeByPageDTO.getPageSize());

        LambdaQueryWrapper<BlogType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(keyword != null && !keyword.isEmpty(), BlogType::getName, keyword);
        queryWrapper.orderByDesc(BlogType::getCreateTime);

        BlogTypeDTO blogTypeDTO = new BlogTypeDTO();
        blogTypeDTO.setPage(this.page(page, queryWrapper));

        return blogTypeDTO;
    }

    @Override
    public BlogTypeDTO adminAll() {
        List blogTypes = this.list();
        BlogTypeDTO blogTypeDTO = new BlogTypeDTO();
        blogTypeDTO.setBlogTypes(blogTypes);
        return blogTypeDTO;
    }

    @Override
    public int countType() {
        return this.baseMapper.getAllBlogTypeWithCount().size();
    }
}