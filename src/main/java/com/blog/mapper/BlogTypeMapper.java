package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogTypeMapper extends BaseMapper<BlogType> {
}