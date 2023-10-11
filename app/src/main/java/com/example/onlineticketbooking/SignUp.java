package com.example.onlineticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.RegisterManager;

public class SignUp extends AppCompatActivity {

    private TextView txtSignIn;
    private Button btnSignUp;
    private EditText etNic, etName, etEmail, etPassword, etCPassword;
    private RegisterManager registerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etNic = findViewById(R.id.etNic);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);
        txtSignIn = (TextView) findViewById(R.id.btn_signIn);
        btnSignUp = findViewById(R.id.btn_signUp);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);

            }
        });

        btnSignUp.setOnClickListener(view -> signUp());

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        registerManager = RegisterManager.getInstance();

    }

    private void signUp() {
        String nic = etNic.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String cPassword = etCPassword.getText().toString();
        String validateMsg = registerManager.validateInputs(nic, name, email, password, cPassword);

        if (validateMsg != null) {
            Toast.makeText(this, validateMsg, Toast.LENGTH_LONG).show();
        } else {
            registerManager.register(nic, name, email, password, () -> handleRegisterSuccess(), error -> handleRegisterFailed(error));
        }

    }

    private void handleRegisterSuccess() {
        Toast.makeText(this, "Register Success", Toast.LENGTH_LONG).show();
        Intent login = new Intent(getApplicationContext(), Login.class);
        startActivity(login);
    }

    private void handleRegisterFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}