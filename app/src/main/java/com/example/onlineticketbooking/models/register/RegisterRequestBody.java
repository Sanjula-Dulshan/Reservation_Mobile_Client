package com.example.onlineticketbooking.models.register;

public class RegisterRequestBody {
    private final String nic;
    private final String name;
    private final String email;
    private final String password;

    private final boolean isTraveler;


    public RegisterRequestBody(String nic, String name, String email, String password) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTraveler = true;
    }
}
