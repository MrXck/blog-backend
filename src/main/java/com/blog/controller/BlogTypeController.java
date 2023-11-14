package com.blog.controller;

import com.blog.dto.type.AddBlogTypeDTO;
import com.blog.dto.type.BlogTypeDTO;
import com.blog.dto.type.GetTypeByPageDTO;
import com.blog.dto.type.UpdateBlogTypeDTO;
import com.blog.service.BlogTypeService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/blogType")
public class BlogTypeController {

    @Autowired
    private BlogTypeService blogTypeService;

    @GetMapping("/all")
    @NoAuthorization
    public BlogTypeDTO all() {
        return blogTypeService.all();
    }

    @GetMapping("/admin/all")
    public BlogTypeDTO adminAll() {
        return blogTypeService.adminAll();
    }


    @GetMapping("/count")
    @NoAuthorization
    public int count() {
        return blogTypeService.countType();
    }

    @PostMapping("/add")
    public String add(@RequestBody @Valid AddBlogTypeDTO addBlogTypeDTO) {
        blogTypeService.add(addBlogTypeDTO);
        return "";
    }

    @PostMapping("/update")
    public String update(@RequestBody @Valid UpdateBlogTypeDTO updateBlogTypeDTO) {
        blogTypeService.updateBlogType(updateBlogTypeDTO);
        return "";
    }

    @GetMapping("/remove/{typeId}")
    public String remove(@PathVariable("typeId") Integer typeId) {
        blogTypeService.removeById(typeId);
        return "";
    }

    @PostMapping("/page")
    @NoAuthorization
    public BlogTypeDTO page(@RequestBody @Valid GetTypeByPageDTO getTypeByPageDTO) {
        return blogTypeService.getByPage(getTypeByPageDTO);
    }
}
