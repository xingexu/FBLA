package com.fbla.parters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // the name of the template file without the .html extension
    }
    
    @GetMapping("/faq")
    public String faq() {
        return "FAQ";
    }
}
