package com.blog.controller;

import com.blog.dto.archives.ArchivesDTO;
import com.blog.dto.archives.GetArchivesDTO;
import com.blog.service.BlogService;
import com.blog.utils.NoAuthorization;
import com.blog.utils.NotControllerResponseAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/archives")
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/get")
    @NoAuthorization
    public ArchivesDTO getArchivesByTime(GetArchivesDTO getArchivesDTO) {
        return blogService.getArchivesByTime(getArchivesDTO);
    }

    @GetMapping("/all")
    @NoAuthorization
    public List all() {
        return blogService.all();
    }
}
