package com.example.onlineticketbooking.models.reservation;

public class ReservationUpdateBody {
    private final String id;
    private final String userId;
    private final String trainId;
    private final String fromStation;
    private final String toStation;
    private final int noOfSeats;
    private final String date;

    private final double totalPrice;

    public ReservationUpdateBody(String id, String userId, String trainId, String fromStation, String toStation, int noOfSeats, String date, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.trainId = trainId;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.noOfSeats = noOfSeats;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}
