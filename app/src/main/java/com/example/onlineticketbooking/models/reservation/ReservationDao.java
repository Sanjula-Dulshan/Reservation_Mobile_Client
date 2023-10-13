package com.example.onlineticketbooking.models.reservation;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.onlineticketbooking.models.login.LoginEntity;

public interface ReservationDao {

    @Query("SELECT * FROM reservation")
    LoginEntity get();

    @Insert
    void insert(ReservationEntity reservation);

    @Update
    void update(ReservationEntity reservation);

    @Query("DELETE FROM reservation")
    void removeAll();
}
