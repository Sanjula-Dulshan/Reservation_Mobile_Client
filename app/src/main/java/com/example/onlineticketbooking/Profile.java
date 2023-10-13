package com.example.onlineticketbooking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.LoginManager;
import com.example.onlineticketbooking.manager.ProfileManager;

public class Profile extends AppCompatActivity {

    EditText etNic, etName, etEmail;
    Button btnUpdate, btnDeactivate;
    private final String loginStateFile = "loginstate";
    private ProfileManager profileManager;

    private String password;
    private String nic;
    private LoginManager loginManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);

        etNic = findViewById(R.id.etNic);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnUpdate = findViewById(R.id.btn_update);
        btnDeactivate = findViewById(R.id.btn_deactivate);

        // Retrieve values from SharedPreferences
        nic = sharedPreferences.getString("nic", "");
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        password = sharedPreferences.getString("password", "");


        etNic.setText(nic);
        etName.setText(name);
        etEmail.setText(email);


        btnUpdate.setOnClickListener(view -> update());
        btnDeactivate.setOnClickListener(view -> deactivate());


        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        profileManager = ProfileManager.getInstance();

    }

    private void update() {
        String nic = etNic.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String validateMsg = profileManager.validateInputs(nic, name, email);

        if (validateMsg != null) {
            Toast.makeText(this, validateMsg, Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Update");
        builder.setMessage("Are you sure you want to update your profile?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed the update, proceed with the update
                profileManager.updateProfile(nic, name, email, password, () -> handleUpdateSuccess(), error -> handleUpdateFailed(error));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the update, do nothing
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void handleUpdateSuccess() {
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_LONG).show();
        Intent login = new Intent(getApplicationContext(), Login.class);
        startActivity(login);

    }

    private void handleUpdateFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void deactivate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deactivation");
        builder.setMessage("Are you sure you want to deactivate your account?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User confirmed the deactivation, proceed with the deactivation
                profileManager.deactivateProfile(nic, () -> handleDeactivateSuccess(), error -> handleDeactivateFailed(error));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the deactivation, do nothing
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void handleDeactivateSuccess() {
        Toast.makeText(this, "Profile deactivated successfully", Toast.LENGTH_LONG).show();
        loginManager = LoginManager.getInstance();
        loginManager.logout();

        Intent login = new Intent(getApplicationContext(), Login.class);
        startActivity(login);
    }

    private void handleDeactivateFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

}