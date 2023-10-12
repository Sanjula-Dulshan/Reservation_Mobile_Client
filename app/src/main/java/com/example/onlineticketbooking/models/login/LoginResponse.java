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


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public boolean isTraveler() {
        return isTraveler;
    }


    public boolean isAgent() {
        return isAgent;
    }


    public boolean isBackOffice() {
        return isBackOffice;
    }


    public boolean isActive() {
        return isActive;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

}
