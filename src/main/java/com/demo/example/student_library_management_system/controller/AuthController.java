package com.demo.example.student_library_management_system.controller;

import com.demo.example.student_library_management_system.model.AppUser;
import com.demo.example.student_library_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);
        return "User registered successfully";
    }
//    @RequestParam String username, @RequestParam String password
    @GetMapping("/login")
    public String login() {
//        passwordEncoder.matches(username,password);
        return "Login successful";
    }
}
