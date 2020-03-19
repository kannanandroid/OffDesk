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
import androidx.recyclerview.widget.RecyclerView;


import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.BookingApiResponse;
import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;

import java.util.List;

public class PastBookingAdapter extends RecyclerView.Adapter<PastBookingAdapter.ViewHolder> {
    private final Context context;
    private final List<BookingHistoryApiResponse.PastBookingHistory> data;

    public PastBookingAdapter(Context context, List<BookingHistoryApiResponse.PastBookingHistory> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PastBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_mybookings_list, parent, false);
        return new PastBookingAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final PastBookingAdapter.ViewHolder holder, int position) {
        BookingHistoryApiResponse.PastBookingHistory response = data.get(position);
        holder.txtLocation.setText(response.getCompanyName() + ", " + response.getLocationName() + ", " + response.getBuildingName() + ", " + response.getFloorName() + ", " + response.getWingName());
        for (int i = 0; i < response.getBook().size(); i++) {
            final LinearLayout newView = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.inflate_layout, null);
            TextView textView1 = (TextView) newView.findViewById(R.id.txtDate);
            TextView textView = (TextView) newView.findViewById(R.id.txtTime);
            textView.setText(response.getBook().get(i).getStartTime() + " " + "to" + " " + response.getBook().get(i).getToTime());
            textView1.setText(response.getBook().get(i).getDate());
            holder.inflateLayout.addView(newView);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtLocation;
        LinearLayout inflateLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
            inflateLayout = (LinearLayout) itemView.findViewById(R.id.inflateLayout);
        }
    }
}