package com.example.onlineticketbooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {
    private List<TrainSchedule> data;

    public TrainAdapter(List<TrainSchedule> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_train, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrainSchedule item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView TrainName, DepTime, ArrTime, availableSeats;
        private Button btnBookNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TrainName = itemView.findViewById(R.id.txtTrainname);
            DepTime = itemView.findViewById(R.id.txtDepartTime);
            ArrTime = itemView.findViewById(R.id.txrArriveTime);
            availableSeats = itemView.findViewById(R.id.txtAvailableSeats);
            btnBookNow  = itemView.findViewById(R.id.btnBookNow);
        }

        public void bind(TrainSchedule item) {
            TrainName.setText(item.getTrainName());
            DepTime.setText(item.getDeparts());
            ArrTime.setText(item.getArrives());
            availableSeats.setText(item.getAvailableSeats());

            btnBookNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();

                    Intent ticketSummary = new Intent(context, TicketSummary.class);
                    ticketSummary.putExtra("trainName", item.getTrainName());
                    ticketSummary.putExtra("departs", item.getDeparts());
                    ticketSummary.putExtra("arrives", item.getArrives());
                    ticketSummary.putExtra("availableSeats", item.getAvailableSeats());
                    ticketSummary.putExtra("totalPrice", "250.00" );
                    ticketSummary.putExtra("noOfSeats", "3");
                    ticketSummary.putExtra("startStation", "Matara");
                    ticketSummary.putExtra("endStation", "Fort");
                    ticketSummary.putExtra("date", "2023/10/10");



                    context.startActivity(ticketSummary);
                }
            });
        }
    }
}

