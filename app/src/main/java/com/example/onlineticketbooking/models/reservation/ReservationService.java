package com.example.onlineticketbooking.models.reservation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReservationService {

    @POST("train/search")
    Call<ReservationResponse> availableTrains(@Body ReservationRequestBody request);
}
