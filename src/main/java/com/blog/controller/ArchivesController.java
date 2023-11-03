package com.blog.controller;

import com.blog.dto.archives.ArchivesDTO;
import com.blog.dto.archives.GetArchivesDTO;
import com.blog.service.BlogService;
import com.blog.utils.NoAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archives")
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/get")
    @NoAuthorization
    public ArchivesDTO getArchivesByTime(@RequestBody GetArchivesDTO getArchivesDTO) {
        return blogService.getArchivesByTime(getArchivesDTO);
    }

    @GetMapping("/all")
    @NoAuthorization
    public List all() {
        return blogService.all();
    }
}
