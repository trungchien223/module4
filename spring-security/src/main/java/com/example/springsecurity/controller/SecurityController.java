package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        model.addAttribute("page", "home");
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("page", "admin");
        return "login";
    }

    @GetMapping("/user-info")
    public String userInfoPage(Model model) {
        model.addAttribute("page", "user-info");
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("page", "login");
        return "login";
    }
}

