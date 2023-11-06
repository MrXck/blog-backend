package com.blog.dto.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.pojo.Blog;
import com.blog.pojo.BlogType;
import lombok.Data;

import java.util.List;

@Data
public class BlogDTO {
    private IPage<Blog> page;

    private List<Blog> blogs;

    private List<BlogType> blogTypes;

    private Blog blog;
}
