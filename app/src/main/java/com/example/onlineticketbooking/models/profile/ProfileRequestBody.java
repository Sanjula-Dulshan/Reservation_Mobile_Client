package com.example.onlineticketbooking.models.profile;

public class ProfileRequestBody {
    private final String nic;
    private final String name;
    private final String email;
    private final String password;

    private final boolean isTraveler;

    private final boolean isAgent;
    private final boolean isBackOffice;

    public ProfileRequestBody(String nic, String name, String email, String password) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTraveler = true;
        this.isAgent = false;
        this.isBackOffice = false;
    }
}
