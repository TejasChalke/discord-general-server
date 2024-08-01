package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.dto.User;
import com.example.discordgeneralserver.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UsersController {
    static class RegisterData {
        public String uname;
        public String email;
        public String password;
        public String image;
    }

    static class LoginData {
        public String email;
        public String password;
    }

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    private ResponseEntity<ActionResult> registerUser(@RequestBody RegisterData body) {
        ActionResult result = usersService.createUser(body.email, body.password, body.uname, body.image);

        if(result.getStatus().equals("success")) return new ResponseEntity<>(result, HttpStatus.CREATED);
        else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    private ResponseEntity<User> loginUser(@RequestBody LoginData body) {
        User user = usersService.loginUser(body.email, body.password);

        if(user.getError() == null) return new ResponseEntity<>(user, HttpStatus.OK);
        else return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
}
