package com.example.onlineticketbooking.models.search;

public class SearchRequestBody {
    private final int noOfSeats;

    private final String date;
    private final String start;
    private final String end;

    public SearchRequestBody(String start, String end, int noOfSeats, String date) {

        this.start = start;
        this.end = end;
        this.noOfSeats = noOfSeats;
        this.date = date;
    }
}
