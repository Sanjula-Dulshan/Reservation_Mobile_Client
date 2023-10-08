package com.example.onlineticketbooking.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("nic")
    @Expose
    private String nic;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("isTraveler")
    @Expose
    private String isTraveler;

    @SerializedName("isActive")
    @Expose
    private String isActive;

    public User(String nic, String name, String email, String isTraveler, String isActive) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.isTraveler = isTraveler;
        this.isActive = isActive;
    }
}
