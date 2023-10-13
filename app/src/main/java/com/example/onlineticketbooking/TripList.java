package com.example.onlineticketbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.ReservationManager;
import com.example.onlineticketbooking.models.reservation.ReservationResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TripList extends AppCompatActivity implements TripAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve user's NIC (user id) from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("loginstate", Context.MODE_PRIVATE);
        String nic = sharedPreferences.getString("nic", "");

        // Fetch user's reservations list based on NIC
        ReservationManager.getInstance().getReservationDetails(nic, reservationResponses -> {
            // Handle the successful response here
            List<TripHistory> data = new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (ReservationResponse reservationResponse : reservationResponses) {
                String reservationId = reservationResponse.getId();

                Date date = reservationResponse.getDate();
                String formattedDate = dateFormat.format(date);

                // Create TripHistory object from the reservation and add to the data list
                data.add(new TripHistory(
                        formattedDate,
                        reservationResponse.getFromStation(),
                        reservationResponse.getToStation(),
                        reservationResponse.getNoOfSeats(),
                        reservationId
                ));
            }

            // Create and set the adapter with the fetched data
            TripAdapter adapter = new TripAdapter(data, this); // Pass this as the listener
            recyclerView.setAdapter(adapter);
        }, errorMessage -> {
            // Handle error while fetching reservations here
            System.out.println("Error: " + errorMessage);
            Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDeleteClick(TripHistory item) {

        System.out.println("Deleting Item: " + item.getPrice());
        // Handle Delete button click here
        ReservationManager.getInstance().deleteReservationDetails(item.getPrice(), response -> {
            // Handle the successful response here
            System.out.println("Response: " + response);
            Toast.makeText(this, "Reservation deleted successfully", Toast.LENGTH_SHORT).show();
            // You may choose to update the adapter data source and call notifyDataSetChanged()
        }, errorMessage -> {
            // Handle error while deleting reservation here
            System.out.println("Error: " + errorMessage);
            Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public void onUpdateClick(TripHistory item) {
        // Handle Update button click here
        // Create and show the update dialog fragment if needed

        //show update seat dialog fragment
        UpdateSeatsDialogFragment updateSeatDialogFragment = new UpdateSeatsDialogFragment();
        updateSeatDialogFragment.show(getSupportFragmentManager(), "Update Seat Dialog Fragment");

        //get item.getNumberOfSeats() and convert to String
        String noOfSeats = Integer.toString(item.getNumberOfSeats());

        //pass data to update seat dialog fragment
        Bundle bundle = new Bundle();
        bundle.putString("reservationId", item.getPrice());
        bundle.putString("noOfSeats", noOfSeats);
        bundle.putString("fromStation", item.getFromStation());
        bundle.putString("toStation", item.getToStation());
        bundle.putString("date", item.getDate());
        bundle.putString("userId", item.getUser_Id());
        bundle.putString("id", item.getId());

        updateSeatDialogFragment.setArguments(bundle);

        //update seat dialog fragment






    }
}
