package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.model.Rooms;
import com.example.discordgeneralserver.service.ChannelsService;
import com.example.discordgeneralserver.service.MembersService;
import com.example.discordgeneralserver.service.RoomsService;
import com.example.discordgeneralserver.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    static class ChannelsData {
        final public String[] keys = new String[] {"id", "creator_id", "name"};
        public List<Object> values;

        public ChannelsData(List<Object> values) { this.values = values; }
    }

    static class SingleChannelData {
        final public String[] userKeys = new String[] {"user_id", "uname"};
        public List<Object> users;
        public List<Rooms> rooms;

        public SingleChannelData(List<Object> users, List<Rooms> rooms) {
            this.users = users;
            this.rooms = rooms;
        }
    }

    @Autowired
    private ChannelsService channelsService;
    @Autowired
    private MembersService membersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RoomsService roomsService;

    @PostMapping("/")
    private ResponseEntity<ActionResult> createChannel(@RequestBody CreateChannelData body) {
        // add the channel and add creator as a member
        ActionResult result = channelsService.createChannel(body.creator_id, body.name, body.description, body.image);

        if(result.getStatus().equals("success")) {
            result.setMessage("channel created and user added");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            if(result.getMessage().contains("Creation Error")) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            else return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<ChannelsData> getUserChannels(@PathVariable Integer userId) {
        List<Object> channels = channelsService.getChannelList(userId);
        if(channels == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ChannelsData result = new ChannelsData(channels);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{channelId}")
    private ResponseEntity<Object> getChannelData(@PathVariable Integer channelId) {
        // get the list of members with their id and name
        List<Object> users = usersService.getUsersByChannelId(channelId);
        if(users == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // get the list of rooms with their id, name and type
        List<Rooms> rooms = roomsService.findRoomsByChannel(channelId);
        if(rooms == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(new SingleChannelData(users, rooms), HttpStatus.OK);
    }

    @GetMapping("/search")
    private ResponseEntity<ChannelsData> getSearchChannels(@RequestParam String name, @RequestParam(name = "id") Integer userId) {
        List<Object> channels = channelsService.getChannelList(name, userId);
        if(channels == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        ChannelsData result = new ChannelsData(channels);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
