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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineticketbooking.manager.ReservationManager;
import com.example.onlineticketbooking.models.reservation.ReservationResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            Set<String> addedReservationIds = new HashSet<>(); // Set to keep track of added reservation IDs

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Convert ReservationResponse objects to TripHistory objects
            for (ReservationResponse reservationResponse : reservationResponses) {
                // Check if the reservation ID is already added to the list
                if (addedReservationIds.contains(reservationResponse.getId())) {
                    continue;
                }

                String formattedDate = dateFormat.format(reservationResponse.getDate());

                data.add(new TripHistory(
                        formattedDate, // Date to String
                        reservationResponse.getFromStation(),
                        reservationResponse.getToStation(),
                        reservationResponse.getNoOfSeats(),
                        String.valueOf(reservationResponse.getTotalPrice()), // int to String
                        reservationResponse.getId(),
                        reservationResponse.getTrainId()
                ));
            }

            // Create and set the adapter with the fetched data
            TripAdapter adapter = new TripAdapter(data, this);
            recyclerView.setAdapter(adapter);
        }, errorMessage -> {
            // Handle error while fetching reservations here
            Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDeleteClick(TripHistory item) {
        // Handle Delete button click here
        ReservationManager.getInstance().deleteReservationDetails(item.getId(), response -> {
            // Handle the successful response here
            Toast.makeText(this, "Reservation deleted successfully", Toast.LENGTH_SHORT).show();
            // You may choose to update the adapter data source and call notifyDataSetChanged()
        }, errorMessage -> {
            // Handle error while deleting reservation here
            Toast.makeText(this, "Reservation deleted successfully", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        });

        //refresh the TripList activity
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onUpdateClick(TripHistory item) {
        // Handle Update button click here
        // Retrieve user's NIC (user id) from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("loginstate", Context.MODE_PRIVATE);
        String nic = sharedPreferences.getString("nic", "");

        // Pass data to the UpdateSeatsDialogFragment
        Bundle bundle = new Bundle();
        bundle.putString("reservationId", item.getId());
        bundle.putString("noOfSeats", String.valueOf(item.getNumberOfSeats()));
        bundle.putString("fromStation", item.getFromStation());
        bundle.putString("toStation", item.getToStation());
        bundle.putString("date", item.getDate());
        bundle.putString("totalPrice", item.getPrice());
        bundle.putString("userId", nic);
        bundle.putString("trainId", item.getTrain_Id());

        UpdateSeatsDialogFragment updateSeatDialogFragment = new UpdateSeatsDialogFragment();
        updateSeatDialogFragment.setArguments(bundle);

        // Show the dialog fragment using FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        updateSeatDialogFragment.show(fragmentManager, "Update Seat Dialog Fragment");
    }
}
