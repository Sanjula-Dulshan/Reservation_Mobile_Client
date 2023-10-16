package com.example.onlineticketbooking;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    private List<TripHistory> data;
    private OnItemClickListener listener;

    public TripAdapter(List<TripHistory> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TripHistory item = data.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate, txtFromStation, txtToStation, txtSeats, txtPrice;
        private Button btnDelete, btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtFromStation = itemView.findViewById(R.id.txtFromStation);
            txtToStation = itemView.findViewById(R.id.txtToStation);
            txtSeats = itemView.findViewById(R.id.txtSeats);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }

        public void bind(final TripHistory item, final OnItemClickListener listener) {
            txtDate.setText("Date: " + item.getDate());
            txtFromStation.setText("From: " + item.getFromStation());
            txtToStation.setText("To: " + item.getToStation());
            txtSeats.setText("Seats: " + item.getNumberOfSeats());
            txtPrice.setText("Price: " + item.getPrice());

            // Disable the update button if the date is older than 5 days.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tripDate = null;
            try {
                tripDate = sdf.parse(item.getDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Date todayDate = new Date();
            long daysBetween = (tripDate.getTime()) / (1000 * 60 * 60 * 24) - (todayDate.getTime()) / (1000 * 60 * 60 * 24);
            if (daysBetween < 5) {
                btnUpdate.setEnabled(false);
                btnUpdate.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#858482")));
                btnDelete.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#fa5252")));
                btnDelete.setEnabled(false);
            }

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDeleteClick(item);
                    }
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onUpdateClick(item);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onDeleteClick(TripHistory item);
        void onUpdateClick(TripHistory item);
    }
}