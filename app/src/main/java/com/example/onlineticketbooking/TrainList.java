package com.example.onlineticketbooking;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineticketbooking.models.reservation.ReservationResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class TrainList extends AppCompatActivity {

    RecyclerView recyclerView;
    long startTimeInMillis;
    long endTimeInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Get the reservationResponse from the Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("reservationResponse")) {
            ReservationResponse reservationResponse = (ReservationResponse) intent.getSerializableExtra("reservationResponse");

            // Use the reservationResponse object here
            if (reservationResponse != null) {
//                List<ReservationResponse.Train> trainList = reservationResponse.getTrainList();
//                int ticketPrice = reservationResponse.getTicketPrice();
//                int totalPrice = reservationResponse.getTotalPrice();
//
//                System.out.print("trainList " + trainList);
//                System.out.print("ticketPrice " + ticketPrice);
//                System.out.print("totalPrice " + totalPrice);

                addDataToAdapter(reservationResponse);


            }
        }


    }

    private void addDataToAdapter(ReservationResponse reservationResponse) {

//        List<TrainSchedule> data = new ArrayList<>();
//        data.add(new TrainSchedule("Raja Rata Manike", "8:13 AM", "11:20 AM", "30"));
//        data.add(new TrainSchedule("Ruhunu Kumari", "8:40 AM", "12:10 PM", "60"));
//        data.add(new TrainSchedule("Galu Kumari", "9:15 AM", "11:45", "40"));
//
//
//        TrainAdapter adapter = new TrainAdapter(data);
//        recyclerView.setAdapter(adapter);

        List<TrainSchedule> data = new ArrayList<>();

        // Check if reservationResponse is not null and contains a list of trains
        if (reservationResponse != null && reservationResponse.getTrainList() != null) {
            for (ReservationResponse.Train train : reservationResponse.getTrainList()) {
                String trainName = train.getTrainName();

                try {
                    // Parse the start and end times
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
                    inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));  // Set the time zone to UTC

                    Date startDate = inputFormat.parse(train.getStartTime());
                    Date endDate = inputFormat.parse(train.getEndTime());

                    // Format the start and end times to display only time with AM/PM
                    SimpleDateFormat outputFormat = new SimpleDateFormat("h:mm a", Locale.US);
                    String startTime = outputFormat.format(startDate);
                    String endTime = outputFormat.format(endDate);

                    int noOfSeats = train.getNoOfSeats();

                    // Create a TrainSchedule object and add it to the data list
                    TrainSchedule schedule = new TrainSchedule(trainName, startTime, endTime, String.valueOf(noOfSeats));
                    data.add(schedule);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // Create the TrainAdapter with the updated data
            TrainAdapter adapter = new TrainAdapter(data);

            // Set the adapter for your RecyclerView
            recyclerView.setAdapter(adapter);

        }
    }
}
