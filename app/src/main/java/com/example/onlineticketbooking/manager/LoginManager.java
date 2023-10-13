package com.example.onlineticketbooking.manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.core.util.Consumer;

import com.example.onlineticketbooking.Login;
import com.example.onlineticketbooking.models.login.LoginEntity;
import com.example.onlineticketbooking.models.login.LoginRequestBody;
import com.example.onlineticketbooking.models.login.LoginResponse;
import com.example.onlineticketbooking.models.login.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {
    private final DatabaseManager databaseManager;

    private static LoginManager singleton;
    private final LoginService loginService;
    private final String loginStateFile = "loginstate";
    private final String isLoggedInKey = "logged_in";

    public static LoginManager getInstance() {
        if (singleton == null)
            singleton = new LoginManager();

        return singleton;
    }

    private LoginManager() {
        databaseManager = DatabaseManager.getInstance();
        loginService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateCredentials(String nic, String password) {
        if (nic == null || nic.length() == 0)
            return false;

        return password != null && password.length() != 0;
    }

    public void login(
            String nic,
            String password,
            Consumer<LoginResponse> onSuccess,
            Consumer<String> onError
    ) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        LoginRequestBody body = new LoginRequestBody(nic, password);
        loginService.login(body).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        // Perform database insertion in a background thread
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                databaseManager.db().LoginDao().removeAll();
                                insertLoginDetails(loginResponse);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                // This is where you can update the UI or perform any post-database operation logic.
                                if (loginResponse != null) {
                                    System.out.println("\nNIC: " + loginResponse.getNic());
                                    System.out.println("Name: " + loginResponse.getName());
                                    System.out.println("Email: " + loginResponse.getEmail());

                                    Context context = ContextManager.getInstance().getApplicationContext();
                                    // Add toast message for login success
                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();

                                    System.out.print("Navigate to home");

                                    onSuccess.accept(loginResponse);
                                }
                            }
                        }.execute();
                        

                    } else {
                        onError.accept("Unknown error occurred while logging in");
                    }
                } else {
                    // Handle unsuccessful response
                    if (response.code() == 404) {
                        // User not found
                        onError.accept("User with NIC " + nic + " not found");
                    }
                    if (response.code() == 400) {
                        // Password incorrect
                        onError.accept("Your Account Deactivated. Please Contact Us");
                    } else {

                        onError.accept("Something went wrong");

                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onError.accept("Unknown error occurred while logging in");

            }
        });

    }


    private void insertLoginDetails(LoginResponse loginResponse) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.nic = loginResponse.getNic();
        loginEntity.name = loginResponse.getName();
        loginEntity.email = loginResponse.getEmail();
        loginEntity.password = loginResponse.getPassword();
        loginEntity.isAgent = loginResponse.isAgent();
        loginEntity.isTraveler = loginResponse.isTraveler();
        loginEntity.isBackOffice = loginResponse.isBackOffice();

        databaseManager.db().LoginDao().insert(loginEntity);
    }

    public void setLoggedInState(boolean isLoggedIn) {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.apply();

    }

    public void setLoggedInUser(LoginResponse loginResponse) {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putString("nic", loginResponse.getNic());
        editor.putString("name", loginResponse.getName());
        editor.putString("email", loginResponse.getEmail());
        editor.putString("password", loginResponse.getPassword());
        editor.putBoolean("isTraveler", loginResponse.isTraveler());
        editor.putBoolean("isAgent", loginResponse.isAgent());
        editor.putBoolean("isBackOffice", loginResponse.isBackOffice());
        editor.apply();

    }

    public void logout() {
        setLoggedInState(false);
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(context, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public boolean getIsLoggedIn() {
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        return prefs.getBoolean(isLoggedInKey, false);
    }
}
