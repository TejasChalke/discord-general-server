package com.example.discordgeneralserver.respository;

import com.example.discordgeneralserver.model.RoomId;
import com.example.discordgeneralserver.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, RoomId> {
    List<Rooms> findByChannelId(Integer channelId);
    Rooms findFirstByChannelIdAndNameAndType(Integer channelId, String name, String type);
}
