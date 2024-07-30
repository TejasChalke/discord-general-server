package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.User;
import com.example.discordgeneralserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static class RegisterData {
        public String uname;
        public String email;
        public String password;
        public String image;
    }

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private User registerUser(@RequestBody RegisterData body) {
        return userService.createUser(body.email, body.password, body.uname, body.image);
    }
}
