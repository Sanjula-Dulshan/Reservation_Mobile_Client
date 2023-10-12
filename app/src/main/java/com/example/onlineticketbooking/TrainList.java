package com.example.onlineticketbooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineticketbooking.models.search.SearchResponse;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TrainList extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("reservationResponse")) {
            SearchResponse searchResponse = (SearchResponse) intent.getSerializableExtra("reservationResponse");

            if (searchResponse != null) {
                addDataToAdapter(searchResponse);
            }
        }

    }

    private void addDataToAdapter(SearchResponse searchResponse) {


        List<TrainSchedule> data = new ArrayList<>();

        // Check if reservationResponse is not null and contains a list of trains

        if (searchResponse != null && searchResponse.getTrainList() != null) {
            for (SearchResponse.Train train : searchResponse.getTrainList()) {
                String trainId = train.getTrainId();
                String trainName = train.getTrainName();

                try {

                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                    inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));  // Set the time zone to UTC

                    Date startDate = inputFormat.parse(train.getStartTime());
                    Date endDate = inputFormat.parse(train.getEndTime());


                    SimpleDateFormat outputFormat = new SimpleDateFormat("h:mm a", Locale.US);
                    String startTime = outputFormat.format(startDate);
                    String endTime = outputFormat.format(endDate);

                    String startStation = train.getStart();
                    String endStation = train.getEnd();

                    int noOfSeats = searchResponse.getNoOfSeats();
                    int availableSeats = train.getNoOfSeats();
                    String dateTimeString = searchResponse.getDate();
                    String[] dateTimeParts = dateTimeString.split("T");

                    String date = dateTimeParts[0];


                    // Create a TrainSchedule object and add it to the data list
                    TrainSchedule schedule = new TrainSchedule(trainName, startTime, endTime, String.valueOf(noOfSeats));
                    data.add(schedule);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


                    TrainSchedule schedule = new TrainSchedule(trainId, trainName, String.valueOf(availableSeats), startStation, startTime, endStation, endTime, String.valueOf(noOfSeats), String.valueOf(searchResponse.getTotalPrice()), date);
                    data.add(schedule);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            TrainAdapter adapter = new TrainAdapter(data);

            recyclerView.setAdapter(adapter);

        }
    }
}
