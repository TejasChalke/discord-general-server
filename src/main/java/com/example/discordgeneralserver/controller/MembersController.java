package com.example.discordgeneralserver.controller;

import com.example.discordgeneralserver.dto.ActionResult;
import com.example.discordgeneralserver.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/member")
public class MembersController {
    static class MembershipData {
        public Integer channelId;
        public Integer userId;
        public String role;
    }

    @Autowired
    private MembersService membersService;

    @PostMapping("/")
    private ResponseEntity<ActionResult> addMember(@RequestBody MembershipData body) {
        ActionResult result = membersService.addMember(body.userId, body.channelId, body.role);

        if (result.getStatus().equals("success")) return new ResponseEntity<>(result, HttpStatus.OK);
        else if (result.getMessage().equals("internal server error"))
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
