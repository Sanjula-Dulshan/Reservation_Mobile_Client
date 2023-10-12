package com.example.onlineticketbooking.models.reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReservationService {

    @GET("Reservation/user/{userId}")
    Call<List<ReservationResponse>> getReservationDetails(@Path("userId") String userId);


}
