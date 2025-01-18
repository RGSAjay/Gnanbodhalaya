package com.gnanbodhalaya.onlinelearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/student-home")
    public String studentHome() {
        return "student-home";
    }

    @GetMapping("/trainer-home")
    public String trainerHome() {
        return "trainer-home";
    }

    @GetMapping("/admin-home")
    public String adminHome() {
        return "admin-home";
    }
}
