package com.blog.dto.type;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GetTypeByPageDTO {
    private String keyword;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "不能小于1")
    private Integer pageSize;

    @NotNull(message = "页数不能为空")
    @Min(value = 1, message = "不能小于1")
    private Integer pageNum;
}
