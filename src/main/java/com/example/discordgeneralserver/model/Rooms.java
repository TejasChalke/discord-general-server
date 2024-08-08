package com.example.discordgeneralserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(RoomId.class)
public class Rooms {
    @Id
    @Column(name = "channel_id")
    private Integer channelId;
    @Id
    private String name;
    private String type;

    public Rooms() {}

    public Rooms(Integer channelId, String name, String type) {
        this.channelId = channelId;
        this.name = name;
        this.type = type;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
