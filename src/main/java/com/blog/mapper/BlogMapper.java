package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("SELECT concat(YEAR(post.create_time), '年' ,MONTH(post.create_time), '月') as date, count(*) as count FROM blog AS post left join blog_type bt on bt.id = post.type_id group by date order by DATE(post.create_time) desc;")
    List<Map> getArchives();

}