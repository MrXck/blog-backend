package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.dto.archives.ArchivesDTO;
import com.blog.dto.archives.GetArchivesDTO;
import com.blog.dto.blog.BlogDTO;
import com.blog.dto.blog.GetBlogByPageDTO;
import com.blog.mapper.BlogMapper;
import com.blog.pojo.Blog;
import com.blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public BlogDTO getByPage(GetBlogByPageDTO getBlogByPageDTO) {
        String keyword = getBlogByPageDTO.getKeyword();
        Integer blogId = getBlogByPageDTO.getBlogId();
        Page<Blog> page = new Page<>(1, getBlogByPageDTO.getPageSize());

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();

        if (keyword != null && !keyword.isEmpty()) {
            queryWrapper.like("title", keyword);
        }

        if (blogId != null) {
            queryWrapper.lt("id", blogId);
        }

        queryWrapper.select("id", "title", "LEFT(content, 200) content", "create_time", "update_time", "type_id", "image");
        queryWrapper.orderByDesc("create_time");

        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setPage(this.page(page, queryWrapper));

        return blogDTO;
    }

    @Override
    public ArchivesDTO getArchivesByTime(GetArchivesDTO getArchivesDTO) {
        LocalDate time = getArchivesDTO.getTime();
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

}