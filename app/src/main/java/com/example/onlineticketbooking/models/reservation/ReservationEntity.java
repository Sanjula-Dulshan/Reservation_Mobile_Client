package com.example.onlineticketbooking.models.reservation;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation")
public class ReservationEntity {

    @PrimaryKey
    @NonNull
    public String userId;
    public String trainId;
    public String fromStation;
    public String toStation;
    public int noOfSeats;
    public String date;
    public double totalPrice;

    public static ReservationEntity fromDto(ReservationDto dto) {
        ReservationEntity entity = new ReservationEntity();
        entity.userId = dto.userId;
        entity.trainId = dto.trainId;
        entity.fromStation = dto.fromStation;
        entity.toStation = dto.toStation;
        entity.noOfSeats = dto.noOfSeats;
        entity.date = dto.date;
        entity.totalPrice = dto.totalPrice;
        return entity;
    }
}
