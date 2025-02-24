package com.hiredin.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hiredin.app.model.User;
import com.hiredin.app.service.AuthService;

//@Controller
public class AuthController {
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @GetMapping({"/register", "/signup"}) // Handle both URLs
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//        return "templates/register";
//    }
//
//    @PostMapping("/register")
//    public String register(@ModelAttribute User user, Model model) {
//        try {
//            authService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
//            return "redirect:/login?registered=true";
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "templates/register";
//        }
//    }
}