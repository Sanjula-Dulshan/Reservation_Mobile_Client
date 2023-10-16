package com.example.onlineticketbooking;

import static com.example.onlineticketbooking.R.id;
import static com.example.onlineticketbooking.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.ReservationManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketSummary extends Activity {

    String userId, trainName, trainId, departs, arrives, availableSeats, totalPrice, noOfSeats, startStation, endStation, date;
    TextView txtTrainName, txtTimeStartEnd, txtAvailableSeats, txtTotalPrice, txtNoOfSeats, txtStartStation, txtEndStation, txtDate;
    Button btnConfirmBooking, btnCancel;
    ReservationManager reservationManager;

    private final String loginStateFile = "loginstate";


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
        btnConfirmBooking = findViewById(id.btnConfirmBooking);
        btnCancel = findViewById(id.btnCancel);

        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("nic", "");


        Intent intent = getIntent();
        trainName = intent.getStringExtra("trainName");
        trainId = intent.getStringExtra("trainId");
        departs = intent.getStringExtra("departs");
        arrives = intent.getStringExtra("arrives");
        availableSeats = intent.getStringExtra("availableSeats");
        totalPrice = intent.getStringExtra("totalPrice");
        noOfSeats = intent.getStringExtra("noOfSeats");
        startStation = intent.getStringExtra("startStation");
        endStation = intent.getStringExtra("endStation");
        date = intent.getStringExtra("date");

        System.out.println("Train Date: " + date);

        txtTrainName.setText(capitalizeFirstLetterOfEachWord(trainName));
        txtTimeStartEnd.setText(departs + " - " + arrives);
        txtAvailableSeats.setText(availableSeats);
        txtTotalPrice.setText(totalPrice);
        txtNoOfSeats.setText(noOfSeats);
        txtStartStation.setText(capitalizeFirstLetterOfEachWord(startStation));
        txtEndStation.setText(capitalizeFirstLetterOfEachWord(endStation));
        txtDate.setText(date);

        btnConfirmBooking.setOnClickListener(view -> addReservation());

        btnCancel.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), Home.class);
            startActivity(intent1);
        });

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        reservationManager = ReservationManager.getInstance();

    }

    private void addReservation() {
        System.out.println("Train Date 2: " + date);
        reservationManager.addReservation(userId, trainId, startStation, endStation, Integer.parseInt(noOfSeats), date, Double.parseDouble(totalPrice), () -> handleAddReservationSuccess(), error -> handleAddReservationFailed(error));

    }

    private void handleAddReservationSuccess() {
        Toast.makeText(this, "Reservation added successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }

    private void handleAddReservationFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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
