package com.example.onlineticketbooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

        seatInputValidation();

        // Set the minimum date to today
        calendar.set(year, month, day);
        long minDate = calendar.getTimeInMillis();

        // Set the maximum date to 30 days from today
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        long maxDate = calendar.getTimeInMillis();

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SelectTrip.this, (view, year, month, dayOfMonth) -> {
                    String formattedDate = String.format(Locale.US, "%04d-%02d-%02d", year, month + 1, dayOfMonth);

                    SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year, month, dayOfMonth);

                    originalFormattedDate = originalDateFormat.format(selectedCalendar.getTime());

                    etDate.setText(formattedDate);
                }, year, month, day);

                datePickerDialog.getDatePicker().setMinDate(minDate);
                datePickerDialog.getDatePicker().setMaxDate(maxDate);

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

        if (!validateFields(from, to, seat, date)) {
            return;
        }

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

    private boolean validateFields(String from, String to, String seat, String date) {
        if (from.isEmpty()) {
            spnFrom.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a starting point", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (to.isEmpty()) {
            spnTo.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a destination", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (from.equals(to)) {
            spnTo.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a different destination", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (seat.isEmpty()) {
            etSeat.requestFocus();
            Toast.makeText(getApplicationContext(), "Please enter the number of seats", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (date.isEmpty()) {
            etDate.requestFocus();
            Toast.makeText(getApplicationContext(), "Please select a date", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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

    private void seatInputValidation() {
        // Custom InputFilter to restrict input to a single digit (1, 2, 3, or 4)
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // If trying to enter more than one character, reject it
                if ((dstart == dend && dend != 0) || (end - start) > 1)
                    return "";

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);
                    if (currentChar != '1' && currentChar != '2' &&
                            currentChar != '3' && currentChar != '4') {
                        // Clear the field if an invalid digit is entered
                        etSeat.setText("");
                        return "";
                    }
                }
                return null;  // Accept the input
            }
        };

        etSeat.setFilters(new InputFilter[]{inputFilter});

        etSeat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    char pressedChar = (char) event.getUnicodeChar();
                    if (pressedChar != '1' && pressedChar != '2' &&
                            pressedChar != '3' && pressedChar != '4') {
                        etSeat.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }

}


