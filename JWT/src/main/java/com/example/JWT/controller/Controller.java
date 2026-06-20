package com.example.JWT.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JWT.dto.AuthResponse;
import com.example.JWT.dto.LoginRequest;
import com.example.JWT.dto.RegisterRequest;
import com.example.JWT.entity.User;
import com.example.JWT.repository.UserRepository;
import com.example.JWT.service.JwtService;
import com.example.JWT.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authe")
@RequiredArgsConstructor

public class Controller {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")

    public String register(@RequestBody RegisterRequest request){
        userService.register(request);
        return "User Created";

    }
    @PostMapping("/login")

    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(()-> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
    @GetMapping("/validate")
    public String validate(){
        return "JWT implemented";
    }
    
}
