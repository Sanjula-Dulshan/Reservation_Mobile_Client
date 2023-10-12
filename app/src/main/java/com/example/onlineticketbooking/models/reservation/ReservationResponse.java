package com.example.onlineticketbooking.models.reservation;


import java.util.List;

public class ReservationResponse {
    private List<Train> trainList;
    private int ticketPrice;
    private int totalPrice;

    public List<Train> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<Train> trainList) {
        this.trainList = trainList;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Inner Train class
    public static class Train {
        private String trainId;
        private String trainName;
        private String start;
        private String end;
        private String startTime;
        private String endTime;
        private int noOfSeats;


        public String getTrainId() {
            return trainId;
        }

        public void setTrainId(String trainId) {
            this.trainId = trainId;
        }

        public String getTrainName() {
            return trainName;
        }

        public void setTrainName(String trainName) {
            this.trainName = trainName;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getNoOfSeats() {
            return noOfSeats;
        }

        public void setNoOfSeats(int noOfSeats) {
            this.noOfSeats = noOfSeats;
        }
    }
}


