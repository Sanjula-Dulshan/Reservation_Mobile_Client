package com.example.onlineticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.onlineticketbooking.R.id;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txtSignUp = (TextView) findViewById(id.btn_signUp);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);

            }
        });




    }
}