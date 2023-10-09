package com.example.onlineticketbooking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TripList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TripHistory> data = new ArrayList<>();
        data.add(new TripHistory("01/01/2023", "Station A", "Station B", 50, "123"));

        TripAdapter adapter = new TripAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
