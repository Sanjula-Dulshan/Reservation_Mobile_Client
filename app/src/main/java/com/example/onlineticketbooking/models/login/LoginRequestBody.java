package com.example.onlineticketbooking.models.login;

public class LoginRequestBody {
    public String nic;
    public String password;

    public LoginRequestBody(String nic, String password) {
        this.nic = nic;
        this.password = password;
    }
}
