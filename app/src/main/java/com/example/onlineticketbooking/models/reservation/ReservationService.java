package com.example.onlineticketbooking.models.reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservationService {

    @GET("Reservation/user/{userId}")
    Call<List<ReservationResponse>> getReservationDetails(@Path("userId") String userId);

    @POST("Reservation")
    Call<ReservationResponse> addReservation(@Body ReservationRequestBody reservationRequestBody);
    @DELETE("Reservation/{userId}")
    Call<List<ReservationResponse>> deleteReservationDetails(@Path("userId") String userId);

    @PUT("Reservation/{userId}")
    Call<List<ReservationResponse>> updateReservationDetails(@Path("userId") String userId);


}
