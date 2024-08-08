package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.model.Rooms;
import com.example.discordgeneralserver.respository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;

    public List<Rooms> findRoomsByChannel(Integer channelId) {
        try {
            return roomsRepository.findByChannelId(channelId);
        } catch (Exception e) {
            System.out.println("Error getting rooms by channel id: " + e.getMessage());
            return null;
        }
    }
}
