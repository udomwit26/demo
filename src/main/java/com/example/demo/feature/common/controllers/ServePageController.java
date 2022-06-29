package com.example.demo.feature.common.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class ServePageController {

    @GetMapping("/home")
    public String home() {
        return "home/home.html";
    }

    @GetMapping("/js/home.js")
    public String homejs() {
        return "home/home.js";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login.html";
    }

    @GetMapping("/js/login.js")
    public String loginjs() {
        return "login/login.js";
    }
}
