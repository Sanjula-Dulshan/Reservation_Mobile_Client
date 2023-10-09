package com.example.onlineticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.LoginManager;

public class Home extends AppCompatActivity {

    private Button btnLogOut;
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


        btnLogOut = (Button)findViewById(R.id.btnLogout);

        btnLogOut.setOnClickListener(view -> logout());


    }

    private void logout() {

        loginManager = LoginManager.getInstance();
        loginManager.logout();

    }
}