package com.example.onlineticketbooking.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ReservationResponse {

    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public class Reservation {



        private String id;


        private String userId;


        private String trainId;


        private String fromStation;


        private String toStation;


        private int noOfSeats;


        private Date date;


        private int totalPrice;

        private boolean isAgent;

        private Date CreatedAt;

        private Date UpdatedAt;

        // Other fields, getters, and setters as needed

        public String getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getTrainId() {
            return trainId;
        }

        public String getFromStation() {
            return fromStation;
        }

        public String getToStation() {
            return toStation;
        }

        public int getNoOfSeats() {
            return noOfSeats;
        }

        public Date getDate() {
            return date;
        }

        public int getTotalPrice() {
            return totalPrice;
        }
    }
}
