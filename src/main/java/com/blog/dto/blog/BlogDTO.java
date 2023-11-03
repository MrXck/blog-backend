package com.blog.dto.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.pojo.Blog;
import lombok.Data;

import java.util.List;

@Data
public class BlogDTO {
    private IPage<Blog> page;

    private List<Blog> blogs;

    private Blog blog;
}
