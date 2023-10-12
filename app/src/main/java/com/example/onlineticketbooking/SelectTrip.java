package com.example.onlineticketbooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineticketbooking.manager.ContextManager;
import com.example.onlineticketbooking.manager.ReservationManager;
import com.example.onlineticketbooking.models.search.SearchResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelectTrip extends AppCompatActivity {

    Button btnSearch;
    EditText etDate, etSeat;
    Spinner spnFrom, spnTo;
    DatePickerDialog datePickerDialog;

    ReservationManager reservationManager;
    // LocalDateTime dateTime;

    Date dateTime;

    String originalFormattedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_trip);

        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);
        etSeat = findViewById(R.id.etSeat);
        etDate = findViewById(R.id.etDate);
        btnSearch = findViewById(R.id.btn_search);
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SelectTrip.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, dayOfMonth);

                        // Original date with time pattern
                        SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);

                        // Format the date in the original pattern (yyyy-MM-ddTHH:mm:ss.SSSZ)
                        originalFormattedDate = originalDateFormat.format(calendar.getTime());

                        // Set the formatted dates to etDate
                        etDate.setText(formattedDate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });

        btnSearch.setOnClickListener(view -> searchAvailableTrain());

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        reservationManager = ReservationManager.getInstance();

    }

    private void searchAvailableTrain() {


        String from = spnFrom.getSelectedItem().toString().toLowerCase();
        String to = spnTo.getSelectedItem().toString().toLowerCase();
        String seat = etSeat.getText().toString();
        String date = etDate.getText().toString();

        validateFields(from, to, seat, date);

        reservationManager.getAvailableTrain(
                from,
                to,
                Integer.parseInt(seat),
                originalFormattedDate,
                searchResponse -> {
                    handleSearchSuccess(searchResponse);

                },
                error -> handleSearchFailed(error));


    }

    private void validateFields(String from, String to, String seat, String date) {

        if (from.isEmpty()) {
            spnFrom.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a starting point", Toast.LENGTH_SHORT).show();
        }

        if (to.isEmpty()) {
            spnTo.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a destination", Toast.LENGTH_SHORT).show();
        }

        if (from.equals(to)) {
            spnTo.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a different destination", Toast.LENGTH_SHORT).show();
        }

        if (Integer.parseInt(seat) <= 0) {
            etSeat.requestFocus();
            Toast.makeText(getApplicationContext(), "Please enter the number of seats", Toast.LENGTH_SHORT).show();
        }

        if (date.isEmpty()) {
            etDate.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a date", Toast.LENGTH_SHORT).show();
        }

    }


    private void handleSearchSuccess(SearchResponse searchResponse) {
        // Print the reservationResponse for debugging
        System.out.print("reservationResponse " + searchResponse);
        Toast.makeText(this, "Search Success", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TrainList.class);
        intent.putExtra("reservationResponse", searchResponse);
        startActivity(intent);
    }

    private void handleSearchFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}


