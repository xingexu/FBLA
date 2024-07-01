package com.fbla.parters.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fbla.parters.service.icon.IconService;

@Controller
public class IconController {

    private final IconService iconService;

    public IconController(IconService iconService) {
        this.iconService = iconService;
    }

    @GetMapping("/icons/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(iconService.downloadIcon(filename));

    }
}