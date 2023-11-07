package com.blog.dto.type;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateBlogTypeDTO {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotEmpty(message = "名称不能为空")
    private String name;
}
