package com.example.onlineticketbooking;

public class TripHistory {
    private String date;

    public String getId() {
        return id;
    }

    private String id;
    private String fromStation;
    private String toStation;
    private int numberOfSeats;
    private String price;

    private String trainId;

    public String getTrain_Id() {
        return trainId;
    }

    public String getUser_Id() {
        return user_Id;
    }

    private String user_Id;

    public TripHistory(String date, String fromStation, String toStation, int numberOfSeats, String price, String id, String trtid) {
        this.date = date;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
        this.id = id;
        this.trainId = trtid;
    }

    public String getDate() {
        return date;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getPrice() {
        return price;
    }
}