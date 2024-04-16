package com.example.homework_16.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping
    public String home() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request, Authentication authentication) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
            return "pages/login";
        }
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "pages/login";
    }

    @GetMapping("/registration")
    public String registration(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        return "pages/registration";
    }

    @GetMapping("/profile")
    public String profile() {
        return "pages/profile";
    }
}
