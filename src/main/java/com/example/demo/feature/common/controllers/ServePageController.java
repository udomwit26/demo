package com.example.demo.feature.common.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServePageController {

    @GetMapping("/pages/home")
    public String home() {
        return "home/home.html";
    }

    @GetMapping("/pages/home/js/home.js")
    public String js() {
        return "home/home.js";
    }
}
