package com.example.onlineticketbooking;

import android.os.Bundle;
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
        data.add(new TrainSchedule("Express Train - Beliatta to Maradana", "4216 4456 7854 67", "18", "123"));

        TrainAdapter adapter = new TrainAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
