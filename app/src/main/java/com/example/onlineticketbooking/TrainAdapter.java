package com.example.onlineticketbooking;

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
        private TextView txtCardName, txtCardNumber, txtExpDate, txtCV;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCardName = itemView.findViewById(R.id.txtTrainname);
            txtCardNumber = itemView.findViewById(R.id.txtDepartTime);
            txtExpDate = itemView.findViewById(R.id.txrArriveTime);
            txtCV = itemView.findViewById(R.id.txtPrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(TrainSchedule item) {
            txtCardName.setText(item.getCardName());
            txtCardNumber.setText(item.getCardNumber());
            txtExpDate.setText(item.getExpDate());
            txtCV.setText(item.getPrice());
        }
    }
}

