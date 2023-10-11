package com.example.onlineticketbooking.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static NetworkManager singleton;

    private Retrofit retrofit;
    private final String baseUrl = "http://192.168.5.11:5000/api/";
    private ConnectivityManager connectivityManager;

    public static NetworkManager getInstance() {
        if (singleton == null)
            singleton = new NetworkManager();
        return singleton;
    }

    private NetworkManager() {
        //TODO: Setup Retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    //Check the internet connection
    public boolean isNetworkAvailable() {
        Context context = ContextManager.getInstance().getApplicationContext();

        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean available = info != null && info.isConnectedOrConnecting();

        if (!available) {
            Toast.makeText(context, "Please connect to the internet and retry", Toast.LENGTH_LONG).show();
        }

        return available;
    }

}
