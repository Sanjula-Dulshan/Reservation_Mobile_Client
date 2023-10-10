package com.example.onlineticketbooking;

public class TrainSchedule {
    private String trainName;
    private String departs;
    private String arrives;
    private String availableSeats;

    public TrainSchedule(String trainName, String departs, String arrives, String availableSeats) {
        this.trainName = trainName;
        this.departs = departs;
        this.arrives = arrives;
        this.availableSeats = availableSeats;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDeparts() {
        return departs;
    }

    public void setDeparts(String departs) {
        this.departs = departs;
    }

    public String getArrives() {
        return arrives;
    }

    public void setArrives(String arrives) {
        this.arrives = arrives;
    }

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }
}