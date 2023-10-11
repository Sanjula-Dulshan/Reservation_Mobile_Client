package com.example.onlineticketbooking;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SelectTrip extends AppCompatActivity {

    Button btnSearch;
    EditText etDate, etSeat;
    Spinner spnFrom, spnTo;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trip);

        btnSearch = findViewById(R.id.btn_search);
        etDate = findViewById(R.id.etDate);
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String from = spnFrom.getSelectedItem().toString();
                String to = spnTo.getSelectedItem().toString();
                String seat = etSeat.getText().toString();
                String date = etDate.getText().toString();

//                Intent trainList = new Intent(getApplicationContext(), TrainList.class);
//                startActivity(trainList);

            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SelectTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
    }
}
