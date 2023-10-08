package com.example.onlineticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineticketbooking.R.id;
import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.LoginManager;

public class Login extends AppCompatActivity {

    private EditText etNic;
    private EditText etPassword;
    private Button btnLogin;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txtSignUp = (TextView) findViewById(id.btn_signUp);

        etNic = findViewById(id.user_nic);
        etPassword = findViewById(id.user_password);
        btnLogin = findViewById(id.btn_login);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent signUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(signUp);

            }
        });

        btnLogin.setOnClickListener(view -> login());

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        loginManager = LoginManager.getInstance();

        checkLoginState();

    }

    private void login() {
        String nic = etNic.getText().toString();
        String password = etPassword.getText().toString();
        boolean isValid = loginManager.validateCredentials(nic, password);

        if (!isValid) {
            Toast.makeText(this, "Invalid NIC or Password", Toast.LENGTH_LONG).show();
        }


        loginManager.login(
                nic,
                password,
                () -> handleLoginSuccess(),
                error -> handleLoginFailed(error));
    }

    private void handleLoginSuccess() {
        loginManager.setLoggedInState(true);
        checkLoginState();
    }

    private void handleLoginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void checkLoginState() {
        System.out.print("loginManager.getIsLoggedIn() "+ loginManager.getIsLoggedIn());
        if (loginManager.getIsLoggedIn()) {
            showMainActivity();
        }
    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}