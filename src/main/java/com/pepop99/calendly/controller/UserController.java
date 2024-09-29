package com.pepop99.calendly.controller;

import com.pepop99.calendly.dto.UserDTO;
import com.pepop99.calendly.model.User;
import com.pepop99.calendly.repository.UserRepository;
import com.pepop99.calendly.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Create a new user.")
    @PostMapping("/save")
    public User save(@RequestBody UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        return userService.save(user);
    }
}
