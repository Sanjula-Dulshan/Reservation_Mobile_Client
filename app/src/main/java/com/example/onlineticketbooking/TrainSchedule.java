package com.example.onlineticketbooking;

public class TrainSchedule {

    private final String trainId;
    private final String trainName;

    private final String availableSeats;

    private final String startStation;
    private final String departs;
    private final String endStation;
    private final String arrives;
    private final String noOfSeats;
    private final String totalPrice;
    private final String date;


    public TrainSchedule(String trainId, String trainName, String availableSeats, String startStation, String departs, String endStation, String arrives, String noOfSeats, String totalPrice, String date) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.availableSeats = availableSeats;
        this.startStation = startStation;
        this.departs = departs;
        this.endStation = endStation;
        this.arrives = arrives;
        this.noOfSeats = noOfSeats;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getStartStation() {
        return startStation;
    }


    public String getDeparts() {
        return departs;
    }


    public String getEndStation() {
        return endStation;
    }


    public String getArrives() {
        return arrives;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getAvailableSeats() {
        return availableSeats;
    }
}