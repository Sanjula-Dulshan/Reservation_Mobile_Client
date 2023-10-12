package com.example.onlineticketbooking.models.search;


import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {
    private List<Train> trainList;
    private int ticketPrice;
    private int totalPrice;
    private int noOfSeats;
    private String date;

    public List<Train> getTrainList() {
        return trainList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    // Inner Train class
    public static class Train implements Serializable {
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

        public String getTrainName() {
            return trainName;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public int getNoOfSeats() {
            return noOfSeats;
        }
    }
}


