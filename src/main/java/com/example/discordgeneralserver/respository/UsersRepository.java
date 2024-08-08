package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    int countByUname(String uname);
    Users findFirstByEmail(String email);
    Users findFirstByEmailAndPassword(String email, String password);

    @Query(value = "Select u.id, u.uname From users as u " +
            "Where u.id in (" +
            "    Select m.user_id From members as m " +
            "    Where m.channel_id = ?1" +
            ")", nativeQuery = true)
    List<Object> findUsersByChannelId(Integer channelId);
}
