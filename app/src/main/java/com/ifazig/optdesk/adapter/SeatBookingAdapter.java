package com.ifazig.optdesk.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.activity.SeatBookingActivity;
import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;

import java.util.List;

public class SeatBookingAdapter extends RecyclerView.Adapter<SeatBookingAdapter.ViewHolder> {
    private final Context context;
    private final List<MultiValidWorkStationApiResponse.ValidationWorkstation> data;

    public SeatBookingAdapter(Context context, List<MultiValidWorkStationApiResponse.ValidationWorkstation> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SeatBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_seat_item, parent, false);
        return new SeatBookingAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SeatBookingAdapter.ViewHolder holder, int position) {
        MultiValidWorkStationApiResponse.ValidationWorkstation response = data.get(position);
        holder.tv_seat_info_txt_date.setText("Your Booking on:" + " " + response.getDate());
        LinearLayoutManager layoutManager = new GridLayoutManager(context, 4);
        holder.rvlayoutSeat.setLayoutManager(layoutManager);
        SeatBookingAdapterFinal adapter = new SeatBookingAdapterFinal(context, response.getWorkstationlist());
        holder.rvlayoutSeat.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_seat_info_txt_date;
        RecyclerView rvlayoutSeat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_seat_info_txt_date = (TextView) itemView.findViewById(R.id.tv_seat_info_txt_date);
            rvlayoutSeat = (RecyclerView) itemView.findViewById(R.id.rvlayoutSeat);
        }
    }
}