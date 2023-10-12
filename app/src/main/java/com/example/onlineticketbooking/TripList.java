package com.example.onlineticketbooking;

import android.content.Context;
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
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("loginstate", Context.MODE_PRIVATE);
        String nic = sharedPreferences.getString("nic", "");

        // Fetch user's reservations list based on NIC
        ReservationManager.getInstance().getReservationDetails(nic, reservationResponses -> {
            // Handle the successful response here
            List<TripHistory> data = new ArrayList<>();

            // Print the reservationResponses (list of ReservationResponse objects)
            System.out.println("Reservation Responses: " + reservationResponses);

            for (ReservationResponse reservationResponse : reservationResponses) {
                for (ReservationResponse reservation : reservationResponses) {
                    // Create TripHistory objects from the reservations and add to the data list
                    data.add(new TripHistory(
                            "sdasdasd",
                            reservation.getFromStation(),
                            reservation.getToStation(),
                            reservation.getNoOfSeats(),
                            reservation.getId()
                    ));
                }
            }
            // Create and set the adapter with the fetched data
            TripAdapter adapter = new TripAdapter(data);
            adapter.setOnItemClickListener(this); // Set item click listener
            recyclerView.setAdapter(adapter);
        }, errorMessage -> {
            // Handle error while fetching reservations here
            System.out.println("Error: " + errorMessage);

            // Display an error message to the user or perform appropriate error handling actions
            Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDeleteClick(TripHistory item) {
        // Handle Delete button click here
        // You can access the item's properties (date, stations, seats, price) from the 'item' object
        // For example: Toast.makeText(this, "Delete clicked for item with ID: " + item.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateClick(TripHistory item) {
        // Handle Update button click here
        // You can access the item's properties (date, stations, seats, price) from the 'item' object

        // Create a new instance of the UpdateSeatsDialogFragment
        UpdateSeatsDialogFragment dialogFragment = new UpdateSeatsDialogFragment();

        // Pass the selected item data to the dialog fragment if needed
        Bundle bundle = new Bundle();
        bundle.putString("date", item.getDate());
        // Add other item data as needed
        dialogFragment.setArguments(bundle);

        // Show the dialog fragment
        dialogFragment.show(getSupportFragmentManager(), "UpdateSeatsDialogFragment");
    }
}
