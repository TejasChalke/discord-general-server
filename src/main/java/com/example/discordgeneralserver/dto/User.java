package com.example.discordgeneralserver.dto;

public class User {
    private Integer id;
    private Integer tag;
    private String uname;
    private String email;

    private String error;

    public User(String error) {
        this.error = error;
    }

    public User(Integer id, Integer tag, String uname, String email) {
        this.id = id;
        this.tag = tag;
        this.uname = uname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
