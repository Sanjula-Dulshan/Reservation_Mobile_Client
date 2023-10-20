package com.example.onlineticketbooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.onlineticketbooking.manager.ReservationManager;
import com.example.onlineticketbooking.models.reservation.ReservationRequestBody;
import com.example.onlineticketbooking.models.reservation.ReservationUpdateBody;

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

        Button btnUpdateSeats, btnCancel;
        btnUpdateSeats = view.findViewById(R.id.btnUpdateSeats);

        btnUpdateSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated seat count from the textview
                String updatedSeatCount = txtSeats.getText().toString();
                int updatedSeatCountInt = Integer.parseInt(updatedSeatCount);

                if (updatedSeatCountInt < 1 || updatedSeatCountInt > 4) {
                    Toast.makeText(getContext(), "Please enter a valid seat count", Toast.LENGTH_SHORT).show();
                    return;
                }

                String longDate = date+"T00:00:00Z";

                //convert totalPrice to double
                double price = Double.parseDouble(totalPrice);

                ReservationUpdateBody body = new ReservationUpdateBody(reservationId, userId, trainId, fromStation, toStation, updatedSeatCountInt, longDate, price);

                System.out.println("Class created");                // update reservation
                ReservationManager.getInstance().updateReservation(reservationId, body,response -> {
                    // Handle the successful response here
                    Toast.makeText(getContext(), "Reservation deleted successfully", Toast.LENGTH_SHORT).show();
                    // You may choose to update the adapter data source and call notifyDataSetChanged()
                }, errorMessage -> {
                    // Handle error while deleting reservation here
                    Toast.makeText(getContext(), "Error:"+errorMessage, Toast.LENGTH_SHORT).show();
                });

                Toast.makeText(getContext(), "Seats updated successfully", Toast.LENGTH_SHORT).show();

                //refresh the TripList activity
                getActivity().finish();
                startActivity(getActivity().getIntent());

                //go home
                Intent intent = new Intent(getContext(), Home.class);
                startActivity(intent);


                // Close the dialog
                dismiss();
            }
        });

        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the dialog
                //refresh the TripList activity
                getActivity().finish();
                startActivity(getActivity().getIntent());
                dismiss();
            }
        });

        return view;
    }
}
