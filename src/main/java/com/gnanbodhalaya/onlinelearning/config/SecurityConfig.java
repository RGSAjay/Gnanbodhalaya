package com.gnanbodhalaya.onlinelearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/student-home").hasRole("STUDENT")
                    .requestMatchers("/trainer-home").hasRole("TRAINER")
                    .requestMatchers("/admin-home").hasRole("ADMIN")
                    .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login") // Custom login page
                    .successHandler(customSuccessHandler()) // Redirect based on roles
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout") // Redirect to login after logout
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .findFirst()
                .orElse("");
    
            // Enhanced switch (rule switch)
            switch (role) {
                case "ROLE_STUDENT" -> response.sendRedirect("/student-home");
                case "ROLE_TRAINER" -> response.sendRedirect("/trainer-home");
                case "ROLE_ADMIN" -> response.sendRedirect("/admin-home");
                default -> response.sendRedirect("/login?error");
            }
        };
    }
    
}
