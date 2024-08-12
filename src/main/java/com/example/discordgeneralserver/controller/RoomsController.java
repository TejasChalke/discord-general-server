package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/room")
public class RoomsController {
    static class NewRoomData {
        public String name, type;
        public Integer channelId;

        NewRoomData(Integer channelId, String name, String type) {
            this.channelId = channelId;
            this.name = name;
            this.type = type;
        }
    }

    @Autowired
    private RoomsService roomsService;

    @PostMapping("/")
    private ResponseEntity<ActionResult> createRoom(@RequestBody NewRoomData body) {
        ActionResult result = roomsService.createRoom(body.channelId, body.name, body.type);

        if(result.getStatus().equals("success")) return new ResponseEntity<>(HttpStatus.CREATED);
        else if(result.getMessage().equals("internal server error")) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
