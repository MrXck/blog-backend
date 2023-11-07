package com.blog.dto.blog;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddBlogDTO {
    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "内容不能为空")
    private String content;

    @NotNull(message = "typeId不能为空")
    private Integer typeId;

    private String image;
}
