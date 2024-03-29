package com.example.mydagger.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

//    @SerializedName("id")
//    @Expose
    private int id;

//    @SerializedName("username")
//    @Expose
    private String username;

//    @SerializedName("email")
//    @Expose
    private String email;

//    @SerializedName("website")
//    @Expose
    private String website;

    public User() {
    }

    public User(int id, String username, String email, String website) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }
}
