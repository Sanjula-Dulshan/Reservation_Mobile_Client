package com.example.onlineticketbooking;

public class TripHistory {
    private String date;
    private String fromStation;
    private String toStation;
    private int numberOfSeats;
    private String price;

    public TripHistory(String date, String fromStation, String toStation, int numberOfSeats, String price) {
        this.date = date;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
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
