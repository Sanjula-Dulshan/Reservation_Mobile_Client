package com.example.onlineticketbooking.models.reservation;

public class ReservationRequestBody {
    private final String userId;
    private final String trainId;
    private final String fromStation;
    private final String toStation;
    private final int noOfSeats;
    private final String date;

    private final double totalPrice;

    public ReservationRequestBody(String userId, String trainId, String fromStation, String toStation, int noOfSeats, String date, double totalPrice) {
        this.userId = userId;
        this.trainId = trainId;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.noOfSeats = noOfSeats;
        this.date = date;
        this.totalPrice = totalPrice;
    }
}
