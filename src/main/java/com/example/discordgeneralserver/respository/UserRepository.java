package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    int countByUname(String uname);
    Users findFirstByEmail(String email);
    Users findFirstByEmailAndPassword(String email, String password);
}
