package com.example.onlineticketbooking.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ReservationResponse {
    @SerializedName("reservations")
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public class Reservation {
        @SerializedName("id")
        private String id;

        @SerializedName("userId")
        private String userId;

        @SerializedName("trainId")
        private String trainId;

        @SerializedName("fromStation")
        private String fromStation;

        @SerializedName("toStation")
        private String toStation;

        @SerializedName("noOfSeats")
        private int noOfSeats;

        @SerializedName("date")
        private Date date;

        @SerializedName("totalPrice")
        private int totalPrice;

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
