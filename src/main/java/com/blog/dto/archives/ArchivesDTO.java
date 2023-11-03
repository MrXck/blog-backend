package com.blog.dto.archives;

import com.blog.pojo.Blog;
import lombok.Data;

import java.util.List;

@Data
public class ArchivesDTO {

    private List<Blog> blogs;
}
