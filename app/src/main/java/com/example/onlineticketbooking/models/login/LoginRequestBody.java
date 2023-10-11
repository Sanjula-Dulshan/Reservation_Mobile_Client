package com.example.onlineticketbooking.models.login;

public class LoginRequestBody {
    private final String nic;
    private final String password;

    public LoginRequestBody(String nic, String password) {
        this.nic = nic;
        this.password = password;
    }
}
