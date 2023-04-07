package com.jwtspringsecurity.jwtssapp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    public List<UserInfoUserDetails> users;
    public Integer nextUserId;

    public UserInfoUserDetailsService() {
        this.users = new ArrayList<>();

        users.add(new UserInfoUserDetails(1, "user-1-username", "$2a$12$Gohb7OrlLMyiIEl0gUmoieAQubCmsgmavcBwcVPhfZcN4V//URnDC", "Allan Gilbert"));
        users.add(new UserInfoUserDetails(2, "user-2-username", "$2a$12$u0rFnahkDcI.qWwdSAVMUOu.MhQXeRmQRIfHMwpbcaE6LPq.UfoFG", "Kimberlly Cardoso"));

        this.nextUserId = 3;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    }

    public List<UserInfoUserDetails> getAllUsers() {
        return users;
    }
}
