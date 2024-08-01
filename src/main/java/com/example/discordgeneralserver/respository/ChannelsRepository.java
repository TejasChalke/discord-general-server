package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.Channels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelsRepository extends JpaRepository<Channels, Integer> {
    Channels findFirstByCreatorIdAndName(Integer creatorId, String name);
}
