package com.example.onlineticketbooking.models.reservation;

public class ReservationRequestBody {
    private final int noOfSeats;

    private final String date;
    private final String start;
    private final String end;

    public ReservationRequestBody(String start, String end, int noOfSeats, String date) {

        this.start = start;
        this.end = end;
        this.noOfSeats = noOfSeats;
        this.date = date;
    }
}
