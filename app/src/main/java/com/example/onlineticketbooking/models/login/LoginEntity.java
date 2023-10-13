package com.example.onlineticketbooking.models.login;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class LoginEntity {

    @PrimaryKey
    @NonNull
    public String nic;
    public String name;
    public String email;
    public String password;
    public boolean isAgent;
    public boolean isTraveler;
    public boolean isBackOffice;

    public static LoginEntity fromDto(LoginDto dto) {
        LoginEntity entity = new LoginEntity();
        entity.nic = dto.nic;
        entity.name = dto.name;
        entity.email = dto.email;
        entity.password = dto.password;
        entity.isAgent = dto.isAgent;
        entity.isTraveler = dto.isTraveler;
        entity.isBackOffice = dto.isBackOffice;
        return entity;
    }
}
