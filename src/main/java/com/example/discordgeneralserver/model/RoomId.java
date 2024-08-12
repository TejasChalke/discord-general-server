package com.example.discordgeneralserver.model;

import java.io.Serializable;
import java.util.Objects;

public class RoomId implements Serializable {
    private Integer channelId;
    private String name;
    private String type;

    public RoomId() {}

    public RoomId(Integer channelId, String name, String type) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomId roomId)) return false;
        return Objects.equals(channelId, roomId.channelId) && Objects.equals(name, roomId.name) && Objects.equals(type, roomId.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, name, type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
