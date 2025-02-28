package com.hiredin.app.controller;

import java.io.IOException;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;

//@Controller
public class MainController {

	
//	@Hidden
//    @RequestMapping("/")
//	public void redirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/swagger-ui.html");
//	}
	
//    @GetMapping("/")
//    public String home() {
//        return "index";  // Loads index.html
//    }

//    @GetMapping("/login")
//    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Invalid username or password.");
//        }
//        return "login";  // Loads login.html
//    }

//    @GetMapping("/dashboard")
//    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        if (userDetails != null) {
//            model.addAttribute("username", userDetails.getUsername());
//            model.addAttribute("roles", userDetails.getAuthorities()); // Show user roles
//        } else {
//            model.addAttribute("username", "Guest");
//            model.addAttribute("roles", "None");
//        }
//        return "dashboard";  // Loads dashboard.html
    }
//}
