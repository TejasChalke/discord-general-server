package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.MemberId;
import com.example.discordgeneralserver.model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends JpaRepository<Members, MemberId> {
    Members findByUserIdAndChannelId(Integer userId, Integer channelId);
}
