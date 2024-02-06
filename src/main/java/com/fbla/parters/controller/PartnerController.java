package com.fbla.parters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fbla.parters.service.PartnerService;

import lombok.extern.slf4j.Slf4j;

@Controller

@Slf4j
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("/partners")
    public String listPartners(Model model) {
        model.addAttribute("partners", partnerService.findAll());
        return "partners"; // Name of the Thymeleaf template
    }

    // Additional methods to handle search and filter requests
}
