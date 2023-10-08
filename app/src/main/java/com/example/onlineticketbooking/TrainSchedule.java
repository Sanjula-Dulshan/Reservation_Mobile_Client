package com.example.onlineticketbooking;

public class TrainSchedule {
    private String cardName;
    private String cardNumber;
    private String expDate;
    private String price;

    public TrainSchedule(String cardName, String cardNumber, String expDate, String price) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.price = price;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getPrice() {
        return price;
    }
}