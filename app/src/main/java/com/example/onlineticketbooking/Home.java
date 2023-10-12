package com.example.onlineticketbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.LoginManager;

public class Home extends AppCompatActivity {

    private Button btnLogOut, btnBooking, btnTripHistory, btnProfile;


    private LoginManager loginManager;
    private final String loginStateFile = "loginstate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);

        // Retrieve values from SharedPreferences
        String nic = sharedPreferences.getString("nic", "");
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        boolean isTraveler = sharedPreferences.getBoolean("isTraveler", false);
        boolean isAgent = sharedPreferences.getBoolean("isAgent", false);
        boolean isBackOffice = sharedPreferences.getBoolean("isBackOffice", false);

        System.out.println("NIC>>: " + nic);
        System.out.println("Name: " + name);


        btnLogOut = findViewById(R.id.btnLogout);
        btnBooking = findViewById(R.id.btnBook);
        btnTripHistory = findViewById(R.id.btnTripHistory);
        btnProfile = findViewById(R.id.btnProfile);

        btnLogOut.setOnClickListener(view -> logout());
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectTrip = new Intent(getApplicationContext(), SelectTrip.class);
                startActivity(selectTrip);

            }
        });

        btnTripHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tripHistory = new Intent(getApplicationContext(), TripList.class);
                startActivity(tripHistory);

            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(getApplicationContext(), Profile.class);
                startActivity(profile);

            }
        });


    }

    private void logout() {

        loginManager = LoginManager.getInstance();
        loginManager.logout();

    }
}