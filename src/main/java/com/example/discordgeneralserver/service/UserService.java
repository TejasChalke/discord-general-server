package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.dto.User;
import com.example.discordgeneralserver.model.Users;
import com.example.discordgeneralserver.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password, String uname, String image) {
        try {
            Users existingUser = userRepository.findFirstByEmail(email);
            if(existingUser != null) throw new Exception("Creation Error: User with email already exists");

            int tag = userRepository.countByUname(uname);
            if(tag == 9999) throw new Exception("Creation Error: User name cannot be used");
            else tag++;

            Users createdUser = userRepository.saveAndFlush(new Users(uname, tag, password, email, image));
            return new User(createdUser.getId(), createdUser.getTag(), createdUser.getUname(), createdUser.getEmail());
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error creating user: " + error);

            if(error.contains("Creation Error")) return new User(error);
            else return new User("Internal Server Error");
        }
    }
}
