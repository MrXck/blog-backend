package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.archives.ArchivesDTO;
import com.blog.dto.archives.GetArchivesDTO;
import com.blog.dto.blog.AddBlogDTO;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.dto.blog.UpdateBlogDTO;
import com.blog.exception.APIException;
import com.blog.mapper.BlogMapper;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogType;
import com.blog.service.BlogService;
import com.blog.service.BlogTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogTypeService blogTypeService;

    @Override
    public BlogDTO getByPage(GetBlogByPageDTO getBlogByPageDTO) {
        String keyword = getBlogByPageDTO.getKeyword();
        Integer blogId = getBlogByPageDTO.getBlogId();
        Integer typeId = getBlogByPageDTO.getTypeId();
        Page<Blog> page = new Page<>(1, getBlogByPageDTO.getPageSize());

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();

        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like("title", keyword);
        }

        if (blogId != null) {
            queryWrapper.lt("id", blogId);
        }

        if (typeId != null) {
            queryWrapper.eq("type_id", typeId);
        }

        queryWrapper.select("id", "title", "LEFT(content, 200) content", "create_time", "update_time", "type_id", "image");
        queryWrapper.orderByDesc("create_time");

        BlogDTO blogDTO = new BlogDTO();
        Page<Blog> page1 = this.page(page, queryWrapper);

        List<Blog> records = page1.getRecords();

        List<Integer> typeIds = new ArrayList<>();

        for (Blog record : records) {
            Integer typeId1 = record.getTypeId();
            if (!typeIds.contains(typeId1)) {
                typeIds.add(typeId1);
            }
        }

        if (!typeIds.isEmpty()) {
            LambdaQueryWrapper<BlogType> blogTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blogTypeLambdaQueryWrapper.in(BlogType::getId, typeIds);
            List<BlogType> list = blogTypeService.list(blogTypeLambdaQueryWrapper);

            blogDTO.setBlogTypes(list);
        }

        blogDTO.setPage(page1);

        return blogDTO;
    }

    @Override
    public ArchivesDTO getArchivesByTime(GetArchivesDTO getArchivesDTO) {
        String time = getArchivesDTO.getTime();
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();

        if (time != null) {
            queryWrapper.ge(Blog::getCreateTime, time + "-01");
            queryWrapper.le(Blog::getCreateTime, time + "-31");
        }

        queryWrapper.select(Blog.class, i -> !"content".equals(i));
        queryWrapper.orderByDesc(Blog::getCreateTime);

        ArchivesDTO archivesDTO = new ArchivesDTO();
        archivesDTO.setBlogs(this.list(queryWrapper));

        return archivesDTO;
    }

    @Override
    public List all() {
        return this.baseMapper.getArchives();
    }

    @Override
    public void add(AddBlogDTO addBlogDTO) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(addBlogDTO, blog);

        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getTitle, addBlogDTO.getTitle());
        if (this.count(queryWrapper) != 0) {
            throw new APIException("已经有这篇博客了");
        }

        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());
        this.save(blog);
    }

    @Override
    public void updateBlog(UpdateBlogDTO updateBlogDTO) {

        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getTitle, updateBlogDTO.getTitle());
        queryWrapper.ne(Blog::getId, updateBlogDTO.getId());
        if (this.count(queryWrapper) != 0) {
            throw new APIException("已经有这篇博客了");
        }

        LambdaUpdateWrapper<Blog> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Blog::getId, updateBlogDTO.getId());
        updateWrapper.set(Blog::getTitle, updateBlogDTO.getTitle());
        updateWrapper.set(Blog::getContent, updateBlogDTO.getContent());
        updateWrapper.set(Blog::getUpdateTime, LocalDateTime.now());
        updateWrapper.set(Blog::getImage, updateBlogDTO.getImage());
        updateWrapper.set(Blog::getTypeId, updateBlogDTO.getTypeId());

        this.update(updateWrapper);
    }

    @Override
    public BlogDTO getPage(GetBlogByPageDTO getBlogByPageDTO) {
        String keyword = getBlogByPageDTO.getKeyword();
        Page<Blog> page = new Page<>(getBlogByPageDTO.getPageNum(), getBlogByPageDTO.getPageSize());

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();

        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like("title", keyword);
        }

        queryWrapper.select("id", "title", "LEFT(content, 200) content", "create_time", "update_time", "type_id", "image");
        queryWrapper.orderByDesc("create_time");

        BlogDTO blogDTO = new BlogDTO();
        Page<Blog> page1 = this.page(page, queryWrapper);

        List<Blog> records = page1.getRecords();

        List<Integer> typeIds = new ArrayList<>();

        for (Blog record : records) {
            Integer typeId1 = record.getTypeId();
            if (!typeIds.contains(typeId1)) {
                typeIds.add(typeId1);
            }
        }

        if (!typeIds.isEmpty()) {
            LambdaQueryWrapper<BlogType> blogTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            blogTypeLambdaQueryWrapper.in(BlogType::getId, typeIds);
            List<BlogType> list = blogTypeService.list(blogTypeLambdaQueryWrapper);

            blogDTO.setBlogTypes(list);
        }

        blogDTO.setPage(page1);

        return blogDTO;
    }

}