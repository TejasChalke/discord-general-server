package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.model.Rooms;
import com.example.discordgeneralserver.respository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {
    @Autowired
    private RoomsRepository roomsRepository;

    public ActionResult createRoom(Integer channelId, String name, String type) {
        try {
            Rooms existingRoom = roomsRepository.findFirstByChannelIdAndNameAndType(channelId, name, type);
            if(existingRoom != null) throw new Exception("Creation Error: room already exists");

            roomsRepository.saveAndFlush(new Rooms(channelId, name, type));
            return new ActionResult("success", "room created successfully");
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error creating new room: " + error);

            if(error.contains("Creation Error")) return new ActionResult("error", "room already exists");
            else return new ActionResult("error", "internal server error");
        }
    }

    public List<Rooms> findRoomsByChannel(Integer channelId) {
        try {
            return roomsRepository.findByChannelId(channelId);
        } catch (Exception e) {
            System.out.println("Error getting rooms by channel id: " + e.getMessage());
            return null;
        }
    }
}
