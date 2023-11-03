package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("select id, title, LEFT(content, 200), create_time createTime, update_time updateTime, type_id typeId, image from blog order by createTime desc limit #{pageSize}")
    List<Blog> getBlogByPage(Integer pageSize);

    @Select("select id, title, LEFT(content, 200), create_time createTime, update_time updateTime, type_id typeId, image from blog where id < #{blogId} order by createTime desc limit #{pageSize}")
    List<Blog> getBlogByPageHaveBlogId(Integer blogId, Integer pageSize);

}