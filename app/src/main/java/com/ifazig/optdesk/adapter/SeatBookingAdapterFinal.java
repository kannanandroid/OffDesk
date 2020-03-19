package com.ifazig.optdesk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;

import java.util.List;

public class SeatBookingAdapterFinal extends RecyclerView.Adapter<SeatBookingAdapterFinal.ViewHolder> {
    private final Context context;
    private final List<MultiValidWorkStationApiResponse.Workstationlist> data;

    public SeatBookingAdapterFinal(Context context, List<MultiValidWorkStationApiResponse.Workstationlist> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SeatBookingAdapterFinal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_gridview_item, parent, false);
        return new SeatBookingAdapterFinal.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SeatBookingAdapterFinal.ViewHolder holder, int position) {
        MultiValidWorkStationApiResponse.Workstationlist response = data.get(position);
        holder.img_seat_info_txt.setText(response.getWorkstationName());
        if (response.getBookingStatus() == 0) {
            holder.img_seat_info_txt.setBackgroundResource(R.drawable.ic_white);
        } else if (response.getBookingStatus() == 1) {
            holder.img_seat_info_txt.setBackgroundResource(R.drawable.ic_brown);
        } else if (response.getBookingStatus() == 2) {
            holder.img_seat_info_txt.setBackgroundResource(R.drawable.ic_yellow);
        }
        holder.img_seat_info_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (response.getBookingStatus() == 1) {
                    Toast.makeText(context, response.getWorkstationName() + "seat already booked", Toast.LENGTH_SHORT).show();
                } else if (response.getBookingStatus() == 0) {
                    response.setBookingStatus(2);
                    notifyDataSetChanged();
                } else if (response.getBookingStatus() == 2) {
                    response.setBookingStatus(0);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView img_seat_info_txt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_seat_info_txt = (TextView) itemView.findViewById(R.id.img_seat_info_txt);
        }
    }
}