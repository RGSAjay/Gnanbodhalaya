package com.gnanbodhalaya.onlinelearning.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gnanbodhalaya.onlinelearning.model.User;
import com.gnanbodhalaya.onlinelearning.repository.UserRepository;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        logger.info("Searching for user with email: {}", email);
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public void save(User user) {
        logger.info("Saving user: {}", user);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);
    }

    // Additional methods for authentication can be added later
}
