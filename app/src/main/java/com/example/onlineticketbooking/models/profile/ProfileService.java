package com.example.onlineticketbooking.models.profile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileService {

    @PUT("user/{nic}")
    Call<ProfileResponse> updateProfile(@Path("nic") String nic, @Body ProfileRequestBody profileRequestBody);

    @PATCH("user/active_deactive/{nic}")
    Call<ProfileResponse> activeDeactivate(@Path("nic") String nic);
}
