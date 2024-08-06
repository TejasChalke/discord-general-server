package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.model.Channels;
import com.example.discordgeneralserver.respository.ChannelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelsService {
    @Autowired
    private ChannelsRepository channelsRepository;
    @Autowired
    private MembersService membersService;

    @Transactional
    public ActionResult createChannel(Integer creator_id, String name, String description, String image) {
        try {
            Channels existingChannel = channelsRepository.findFirstByCreatorIdAndName(creator_id, name);
            if(existingChannel != null) throw new Exception("Creation Error: Channel with the name already exists for the user");

            Channels createdChannel = channelsRepository.save(new Channels(creator_id, name, description, image));
            // add the creator as a member of the channel
            return membersService.addMember(creator_id, createdChannel.getId(), "creator");
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error creating channel: " + error);

            if(error.contains("Creation Error")) return new ActionResult("error", error);
            else return new ActionResult("error", "internal server error");
        }
    }

    public List<Object> getChannelList(Integer userId) {
        try {
            return channelsRepository.findChannelsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Error getting channels for the user: " + e.getMessage());
            return null;
        }
    }

    public List<Object> getChannelList(String name, Integer userId) {
        try {
            return channelsRepository.findChannelByNameAndUserId(userId, name);
        } catch (Exception e) {
            System.out.println("Error getting channels for the specified name and userId: " + e.getMessage());
            return null;
        }
    }
}
