package com.example.onlineticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TrainList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<TrainSchedule> data = new ArrayList<>();
        data.add(new TrainSchedule("Raja Rata Manike", "8:13 AM", "11:20", "30"));
        data.add(new TrainSchedule("Raja Rata Manike", "8:13 AM", "11:20", "30"));
        data.add(new TrainSchedule("Raja Rata Manike", "8:13 AM", "11:20", "30"));




        TrainAdapter adapter = new TrainAdapter(data);
        recyclerView.setAdapter(adapter);


    }
}
