package com.example.onlineticketbooking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TripList extends AppCompatActivity implements TripAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TripHistory> data = new ArrayList<>();
        data.add(new TripHistory("01/01/2023", "Station A", "Station B", 50, "123"));

        TripAdapter adapter = new TripAdapter(data, this); // Pass the listener to the adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(TripHistory item) {
        // Handle Delete button click here
        // You can access the item's properties (date, stations, seats, price) from the 'item' object
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
