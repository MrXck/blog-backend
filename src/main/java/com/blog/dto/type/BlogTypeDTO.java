package com.blog.dto.type;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.pojo.BlogType;
import lombok.Data;

import java.util.List;

@Data
public class BlogTypeDTO {

    private List blogTypes;

    private Page<BlogType> page;
}
