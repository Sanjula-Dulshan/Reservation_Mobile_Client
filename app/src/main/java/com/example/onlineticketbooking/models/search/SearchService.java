package com.example.onlineticketbooking.models.search;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SearchService {

    @POST("train/search")
    Call<SearchResponse> availableTrains(@Body SearchRequestBody request);
}
