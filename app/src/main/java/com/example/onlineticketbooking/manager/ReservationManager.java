package com.example.onlineticketbooking.manager;

import androidx.core.util.Consumer;
import com.example.onlineticketbooking.models.reservation.ReservationResponse;
import com.example.onlineticketbooking.models.reservation.ReservationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManager {
    private static ReservationManager singleton;
    private ReservationService reservationService;

    public static ReservationManager getInstance() {
        if (singleton == null)
            singleton = new ReservationManager();
        return singleton;
    }

    private ReservationManager() {
        reservationService = NetworkManager.getInstance().createService(ReservationService.class);
    }

    public void getReservationDetails(String userId, Consumer<List<ReservationResponse>> onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        System.out.println("ID>>: " + userId);

        reservationService.getReservationDetails(userId).enqueue(new Callback<List<ReservationResponse>>() {
            @Override
            public void onResponse(Call<List<ReservationResponse>> call, Response<List<ReservationResponse>> response) {
                if (response.isSuccessful()) {
                    System.out.println("Response: " + response.body());

                    List<ReservationResponse> reservationResponses = response.body();
                    if (reservationResponses != null && !reservationResponses.isEmpty()) {
                        onSuccess.accept(reservationResponses);
                    } else {
                        onError.accept("Empty or null response received while fetching reservation details");
                    }
                } else {
                    onError.accept("Failed to fetch reservation details: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ReservationResponse>> call, Throwable t) {
                System.out.println("Error Message: " + t.getMessage());
                onError.accept("Unknown error occurred while fetching reservation details");
            }
        });
    }


}
