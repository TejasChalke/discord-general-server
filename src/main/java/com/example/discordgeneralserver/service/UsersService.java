package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.dto.User;
import com.example.discordgeneralserver.model.Users;
import com.example.discordgeneralserver.respository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public ActionResult createUser(String email, String password, String uname, String image) {
        try {
            Users existingUser = usersRepository.findFirstByEmail(email);
            if(existingUser != null) throw new Exception("Creation Error: User with email already exists");

            int tag = usersRepository.countByUname(uname);
            if(tag == 9999) throw new Exception("Creation Error: User name cannot be used");
            else tag++;

            usersRepository.saveAndFlush(new Users(uname, tag, password, email, image));
            return new ActionResult("success", "user created");
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error creating user: " + error);

            if(error.contains("Creation Error")) return new ActionResult("error", error);
            else return new ActionResult("error", "internal server error");
        }
    }

    public User loginUser(String email, String password) {
        try {
            Users existingUser = usersRepository.findFirstByEmailAndPassword(email, password);
            if(existingUser == null) throw new Exception("Login Error: User not found");

            return new User(existingUser.getId(), existingUser.getTag(), existingUser.getUname(), existingUser.getEmail());
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error logging in: " + error);

            if(error.contains("Login Error")) return new User(error);
            else return new User("Internal Server Error");
        }
    }
}
