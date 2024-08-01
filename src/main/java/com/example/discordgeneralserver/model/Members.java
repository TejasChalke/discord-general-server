package com.example.discordgeneralserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(MemberId.class)
public class Members {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "channel_id")
    private Integer channelId;
    private String role;

    public Members() {}

    public Members(Integer userId, Integer channelId, String role) {
        this.userId = userId;
        this.channelId = channelId;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
