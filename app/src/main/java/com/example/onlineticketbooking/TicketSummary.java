package com.example.onlineticketbooking;

import static com.example.onlineticketbooking.R.id;
import static com.example.onlineticketbooking.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TicketSummary extends Activity {

    TextView txtTrainName, txtTimeStartEnd, txtAvailableSeats, txtTotalPrice, txtNoOfSeats, txtStartStation, txtEndStation, txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_ticket_summary);

        txtTrainName = findViewById(id.txtTrainName);
        txtTimeStartEnd = findViewById(id.txtTimeStartEnd);
        txtAvailableSeats = findViewById(id.txtNoOfPassengers);
        txtTotalPrice = findViewById(id.txtTotalPrice);
        txtNoOfSeats = findViewById(id.txtNoOfPassengers);
        txtStartStation = findViewById(id.txtStartStation);
        txtEndStation = findViewById(id.txtEndStation);
        txtDate = findViewById(id.txtDepartureDate);


        Intent intent = getIntent();
        String trainName = intent.getStringExtra("trainName");
        String departs = intent.getStringExtra("departs");
        String arrives = intent.getStringExtra("arrives");
        String availableSeats = intent.getStringExtra("availableSeats");
        String totalPrice = intent.getStringExtra("totalPrice");
        String noOfSeats = intent.getStringExtra("noOfSeats");
        String startStation = intent.getStringExtra("startStation");
        String endStation = intent.getStringExtra("endStation");
        String date = intent.getStringExtra("date");

        txtTrainName.setText(capitalizeFirstLetterOfEachWord(trainName));
        txtTimeStartEnd.setText(departs + " - " + arrives);
        txtAvailableSeats.setText(availableSeats);
        txtTotalPrice.setText(totalPrice);
        txtNoOfSeats.setText(noOfSeats);
        txtStartStation.setText(capitalizeFirstLetterOfEachWord(startStation));
        txtEndStation.setText(capitalizeFirstLetterOfEachWord(endStation));
        txtDate.setText(date);


    }

    private static String capitalizeFirstLetterOfEachWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split("\\s");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstLetter).append(restOfWord).append(" ");
            }
        }

        return result.toString().trim();
    }
}
