package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogTypeMapper extends BaseMapper<BlogType> {

    @Select("select bt.id as id, bt.name as name, count(*) as num from blog_type bt left join blog on bt.id = blog.type_id group by id;")
    List<Map> getAllBlogTypeWithCount();
}