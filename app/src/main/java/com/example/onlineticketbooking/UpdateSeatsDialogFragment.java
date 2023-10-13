package com.example.onlineticketbooking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.onlineticketbooking.manager.ReservationManager;
import com.example.onlineticketbooking.models.reservation.ReservationRequestBody;

public class UpdateSeatsDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_seats, container, false);

        // Retrieve SharedPreferences from arguments bundle
       // SharedPreferences sharedPreferences = (SharedPreferences) getArguments().getSerializable("sharedPreferences");

        // Get the noOfSeats from the arguments
        Bundle args = getArguments();
        String seatCount = args.getString("noOfSeats");
        String reservationId = args.getString("reservationId");
        String trainId = args.getString("trainId");
        String userId = args.getString("userId");
        String fromStation = args.getString("fromStation");
        String toStation = args.getString("toStation");
        String date = args.getString("date");
        String totalPrice = args.getString("totalPrice");

        //convert totalPrice to double
       // double totalPriceDouble = Double.parseDouble(totalPrice);

        //set the textview
        TextView txtSeats = view.findViewById(R.id.editSeats);
        txtSeats.setText(seatCount);

        Button btnUpdateSeats;
        btnUpdateSeats = view.findViewById(R.id.btnUpdateSeats);

        btnUpdateSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated seat count from the textview
                String updatedSeatCount = txtSeats.getText().toString();

//                // Use SharedPreferences from the parent activity
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("updatedSeatCount", updatedSeatCount);
//                editor.apply();

                // Handle the rest of your logic here

                // Close the dialog
                dismiss();
            }
        });

        return view;
    }
}
