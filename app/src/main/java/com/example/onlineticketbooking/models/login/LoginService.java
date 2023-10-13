package com.example.onlineticketbooking.models.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequestBody request);

    
}
