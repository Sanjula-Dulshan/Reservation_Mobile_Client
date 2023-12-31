package com.example.onlineticketbooking.manager;

import android.widget.Toast;

import androidx.core.util.Consumer;

import com.example.onlineticketbooking.models.reservation.ReservationRequestBody;
import com.example.onlineticketbooking.models.reservation.ReservationResponse;
import com.example.onlineticketbooking.models.reservation.ReservationService;
import com.example.onlineticketbooking.models.reservation.ReservationUpdateBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManager {
    private static ReservationManager singleton;
    private final ReservationService reservationService;

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
        

        reservationService.getReservationDetails(userId).enqueue(new Callback<List<ReservationResponse>>() {
            @Override
            public void onResponse(Call<List<ReservationResponse>> call, Response<List<ReservationResponse>> response) {
                if (response.isSuccessful()) {
                    //System.out.println("Response: ABC " + response.body().get(1).getReservations());
                    //System.out.println("Number of elements in the list: " + response.body().size());

                    //check if response is empty
                    if (response.body().isEmpty()) {
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

    public void addReservation(String userId, String trainId, String fromStation, String toStation, int noOfSeats, String date, double totalPrice, Runnable onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        ReservationRequestBody body = new ReservationRequestBody(userId, trainId, fromStation, toStation, noOfSeats, date, totalPrice);
        reservationService.addReservation(body).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful()) {
                    ReservationResponse reservationResponse = response.body();
                    if (reservationResponse != null) {
                        onSuccess.run();
                    } else {
                        onError.accept("Unknown error occurred while adding reservation");
                    }
                } else {
                    onError.accept("Failed to add reservation: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                onError.accept("Unknown error occurred while adding reservation");
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

    public void updateReservation(String reservationId, ReservationUpdateBody updatedBody,Consumer<List<ReservationResponse>> onSuccess, Consumer<String> onError) {
        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }

        reservationService.updateReservationDetails(reservationId, updatedBody).enqueue(new Callback<List<ReservationResponse>>() {
            @Override
            public void onResponse(Call<List<ReservationResponse>> call, Response<List<ReservationResponse>> response) {

                //print updatedBody as a json
                System.out.println("Updated body: " + updatedBody);


                if (response.isSuccessful()) {


                    //Toast.makeText(null, "Reservation updated successfully", Toast.LENGTH_SHORT).show();
                    List<ReservationResponse> reservationResponses = response.body();
                    if (reservationResponses != null && !reservationResponses.isEmpty()) {
                        //send the updated reservation details to the server
                        System.out.println("Updated reservation details: " + reservationResponses);
                        onSuccess.accept(response.body());
                        //call service to update the reservation details


                    } else {
                        System.out.println("Empty or null response received while updating reservation details");
                        onError.accept("Empty or null response received while updating reservation details");
                    }
                } else {
                    System.out.println("Failed to update reservation details: " + response.message());
                    onError.accept("Failed to update reservation details: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<List<ReservationResponse>> call, Throwable t) {
                //onError.accept("Unknown error occurred while updating reservation details")
            }
        });

    }
}
