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
                    //System.out.println("Response: ABC " + response.body().get(1).getReservations());
                    //System.out.println("Number of elements in the list: " + response.body().size());

                    //check if response is empty
                    if(response.body().isEmpty()){
                        System.out.println("No reservations found");
                        onError.accept("No reservations found");
                        return;
                    }

                    List<ReservationResponse> reservationResponses = response.body();
                    if (reservationResponses != null && !reservationResponses.isEmpty()) {

                        // Print the reservationResponses (list of ReservationResponse objects)
                        //System.out.println("Reservation Responses XX: " + reservationResponses.get(0).getReservations());

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

    public void deleteReservationDetails (String userId, Consumer<List<ReservationResponse>> onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        reservationService.deleteReservationDetails(userId).enqueue(new Callback<List<ReservationResponse>>() {


            @Override
            public void onResponse(Call<List<ReservationResponse>> call, Response<List<ReservationResponse>> response) {
                //print id to be deleted
                System.out.println("ID to delete>>: " + userId);
                if (response.isSuccessful()) {
                    List<ReservationResponse> reservationResponses = response.body();
                    if (reservationResponses != null && !reservationResponses.isEmpty()) {
                        onSuccess.accept(reservationResponses);
                    } else {
                        onError.accept("Empty or null response received while deleting reservation details");
                    }
                } else {
                    onError.accept("Failed to delete reservation details: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ReservationResponse>> call, Throwable t) {
                onError.accept("Unknown error occurred while deleting reservation details");
            }
        });
    }

    public void updateReservationDetails (String userId, Consumer<List<ReservationResponse>> onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }
    }




}
