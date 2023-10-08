package com.example.onlineticketbooking.models.login;

public class LoginResponse {
    private String nic;
    private String name;
    private String email;
    private String password;
    private boolean isTraveler;
    private boolean isAgent;
    private boolean isBackOffice;
    private boolean isActive;
    private String errorMessage;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTraveler() {
        return isTraveler;
    }

    public void setTraveler(boolean traveler) {
        isTraveler = traveler;
    }

    public boolean isAgent() {
        return isAgent;
    }

    public void setAgent(boolean agent) {
        isAgent = agent;
    }

    public boolean isBackOffice() {
        return isBackOffice;
    }

    public void setBackOffice(boolean backOffice) {
        isBackOffice = backOffice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
