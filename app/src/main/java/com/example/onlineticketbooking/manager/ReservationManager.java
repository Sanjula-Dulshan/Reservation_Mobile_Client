package com.example.onlineticketbooking.manager;

import com.example.onlineticketbooking.models.reservation.ReservationRequestBody;
import com.example.onlineticketbooking.models.reservation.ReservationResponse;
import com.example.onlineticketbooking.models.reservation.ReservationService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManager {

    private static ReservationManager singleton;
    private final ReservationService reservationService;

    public static ReservationManager getInstance() {
        if (singleton == null) {
            singleton = new ReservationManager();
        }
        return singleton;
    }

    private ReservationManager() {
        reservationService = NetworkManager.getInstance().createService(ReservationService.class);

    }

    public void getAvailableTrain(String start, String end, int noOfSeats, String date, Consumer<ReservationResponse> onSuccess, Consumer<String> onError) {

        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        ReservationRequestBody body = new ReservationRequestBody(start, end, noOfSeats, date);
        reservationService.availableTrains(body).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful()) {
                    ReservationResponse reservationResponse = response.body();

                    if (reservationResponse != null) {
                        onSuccess.accept(reservationResponse);
                    } else {
                        onError.accept("Something went wrong");
                    }

                } else {
                    onError.accept("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                onError.accept("Something went wrong");
            }
        });
    }


}
