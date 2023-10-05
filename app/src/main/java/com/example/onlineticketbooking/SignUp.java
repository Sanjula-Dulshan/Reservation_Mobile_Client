package com.example.onlineticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView txtSignIn = (TextView) findViewById(R.id.btn_signIn);


        txtSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(),Login.class);
                startActivity(login);

            }
        });




    }
}