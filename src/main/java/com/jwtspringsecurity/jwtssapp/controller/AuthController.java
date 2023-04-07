package com.jwtspringsecurity.jwtssapp.controller;

import com.jwtspringsecurity.jwtssapp.request.AuthRequest;
import com.jwtspringsecurity.jwtssapp.service.JwtService;
import com.jwtspringsecurity.jwtssapp.config.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private UserInfoUserDetailsService userInfoUserDetailsService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthController(UserInfoUserDetailsService userInfoUserDetailsService,
                   JwtService jwtService,
                   AuthenticationManager authenticationManager) {
        this.userInfoUserDetailsService = userInfoUserDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    @GetMapping("/decode")
    public ResponseEntity<?> decodeToken(@RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        return ResponseEntity.ok(jwtService.decodeToken(token));
    }
}
