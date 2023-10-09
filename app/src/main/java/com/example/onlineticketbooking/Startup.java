package com.example.onlineticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Startup extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Button btnSignUp = (Button) findViewById(R.id.btn_signUp);
        TextView txtSignIn = (TextView) findViewById(R.id.btn_signIn);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(),Login.class);
                startActivity(login);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getApplicationContext(),TripList.class);
                startActivity(signUp);


            }
        });
    }
}