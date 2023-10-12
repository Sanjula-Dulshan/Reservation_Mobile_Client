package com.example.onlineticketbooking.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ReservationResponse {

    private String id;


    private String userId;

    private String trainId;


    private String fromStation;


    private String toStation;


    private int noOfSeats;


    private int totalPrice;

    private boolean isAgent;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isAgent() {
        return isAgent;
    }
}
