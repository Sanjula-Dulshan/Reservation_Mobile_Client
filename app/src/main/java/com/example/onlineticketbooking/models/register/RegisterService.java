package com.example.onlineticketbooking.models.register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("user")
    Call<RegisterResponse> register(@Body RegisterRequestBody registerRequestBody);
}
