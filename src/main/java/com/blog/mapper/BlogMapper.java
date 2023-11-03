package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("select id, title, LEFT(content, 200), create_time createTime, update_time updateTime, type_id typeId, image from blog order by createTime desc limit #{pageSize}")
    List<Blog> getBlogByPage(Integer pageSize);

    @Select("select id, title, LEFT(content, 200), create_time createTime, update_time updateTime, type_id typeId, image from blog where id < #{blogId} order by createTime desc limit #{pageSize}")
    List<Blog> getBlogByPageHaveBlogId(Integer blogId, Integer pageSize);

    @Select("SELECT concat(YEAR(post.create_time), '年' ,MONTH(post.create_time), '月') as date, count(*) as count FROM blog AS post group by date order by DATE(post.create_time) desc;")
    List<Map> getArchives();

}