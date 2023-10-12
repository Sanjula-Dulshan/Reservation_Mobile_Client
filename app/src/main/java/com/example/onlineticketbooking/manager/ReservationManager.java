package com.example.onlineticketbooking.manager;

import com.example.onlineticketbooking.models.search.SearchRequestBody;
import com.example.onlineticketbooking.models.search.SearchResponse;
import com.example.onlineticketbooking.models.search.SearchService;
import java.util.function.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManager {

    private static ReservationManager singleton;
    private final SearchService searchService;


    public static ReservationManager getInstance() {
        if (singleton == null) {
            singleton = new ReservationManager();
        }
        return singleton;
    }

    private ReservationManager() {

        searchService = NetworkManager.getInstance().createService(SearchService.class);

    }

    public void getAvailableTrain(String start, String end, int noOfSeats, String date, Consumer<SearchResponse> onSuccess, Consumer<String> onError) {


        if (!NetworkManager.getInstance().isNetworkAvailable()) {
            onError.accept("No internet connectivity");
            return;
        }


        SearchRequestBody body = new SearchRequestBody(start, end, noOfSeats, date);
        searchService.availableTrains(body).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    SearchResponse searchResponse = response.body();

                    if (searchResponse != null) {
                        onSuccess.accept(searchResponse);

                    } else {
                        onError.accept("Something went wrong");
                    }

                } else {
                    onError.accept("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                onError.accept("Something went wrong");
            }
        });
    }


}
