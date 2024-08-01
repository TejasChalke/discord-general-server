package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.service.ChannelsService;
import com.example.discordgeneralserver.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/channel")
public class ChannelsController {
    static class CreateChannelData {
        public Integer creator_id;
        public String name;
        public String description;
        public String image;
    }

    @Autowired
    private ChannelsService channelsService;
    @Autowired
    private MembersService membersService;

    @PostMapping("/")
    private ResponseEntity<ActionResult> createChannel(@RequestBody CreateChannelData body) {
        // add the channel
        ActionResult result = channelsService.createChannel(body.creator_id, body.name, body.description, body.image);
        if(result.getStatus().equals("error")) {
            if(result.getMessage().contains("Creation Error")) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            else return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // if channel is created extract the id from the message
        String channelId = result.getMessage().substring(result.getMessage().indexOf(";") + 2);
        // add the creator as a member of the channel
        result = membersService.addMember(body.creator_id, Integer.parseInt(channelId), "creator");

        if(result.getStatus().equals("success")) {
            result.setMessage("channel created and user added");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            if(result.getMessage().contains("Creation Error")) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            else return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
