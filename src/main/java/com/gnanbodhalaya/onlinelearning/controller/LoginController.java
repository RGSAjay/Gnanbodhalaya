package com.gnanbodhalaya.onlinelearning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gnanbodhalaya.onlinelearning.model.User;
import com.gnanbodhalaya.onlinelearning.repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String ROLE_STUDENT = "STUDENT";
    private static final String ROLE_TRAINER = "TRAINER";

    @GetMapping
    public String showLoginPage() {
        return "login"; // Return the login page (Thymeleaf template)
    }

    @PostMapping
    public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        logger.info("Attempting to login with email: {}", email);

        // Find user by email
        User user = userRepository.findByEmail(email);
        
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Successful login, redirect based on role
            if (ROLE_STUDENT.equals(user.getRole())) {
                return "redirect:/student/home"; // Redirect to student home page
            } else if (ROLE_TRAINER.equals(user.getRole())) {
                return "redirect:/trainer/home"; // Redirect to trainer home page
            }
        }

        // If authentication fails, log and show an error message
        logger.warn("Invalid login attempt for email: {}", email);
        model.addAttribute("error", "Invalid email or password");
        return "login"; // Return to login page
    }
}
