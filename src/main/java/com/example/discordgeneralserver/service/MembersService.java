package com.example.discordgeneralserver.service;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.model.Members;
import com.example.discordgeneralserver.respository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MembersService {
    @Autowired
    private MembersRepository membersRepository;

    public ActionResult addMember(Integer userId, Integer channelId, String role) {
        try {
            Members existingMember = membersRepository.findByUserIdAndChannelId(userId, channelId);
            if(existingMember != null) throw new Exception("Creation Error: Member already exists");

            membersRepository.saveAndFlush(new Members(userId, channelId, role));
            return new ActionResult("success", "member added to the channel");
        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println("Error adding member: " + error);

            if(error.contains("Creation Error")) return new ActionResult("error", error);
            else return new ActionResult("error", "internal server error");
        }
    }
}
