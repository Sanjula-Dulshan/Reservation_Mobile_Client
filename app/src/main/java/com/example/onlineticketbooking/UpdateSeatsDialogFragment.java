package com.example.onlineticketbooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UpdateSeatsDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_seats, container, false);

        // Get the noOfSeats from the arguments
        Bundle args = getArguments();
        String seatCount = args.getString("noOfSeats");

        //set the textview
        TextView txtSeats = view.findViewById(R.id.editSeats);
        txtSeats.setText(seatCount);


        return view;
    }
}