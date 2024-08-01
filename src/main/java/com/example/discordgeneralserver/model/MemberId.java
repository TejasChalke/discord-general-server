package com.example.discordgeneralserver.model;

import java.io.Serializable;
import java.util.Objects;

public class MemberId implements Serializable {
    private Integer userId;

    private Integer channelId;

    public MemberId() {}

    public MemberId(Integer userId, Integer channelId) {
        this.userId = userId;
        this.channelId = channelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberId memberId)) return false;
        return Objects.equals(userId, memberId.userId) && Objects.equals(channelId, memberId.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, channelId);
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
}