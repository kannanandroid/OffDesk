package com.ifazig.optdesk.adapter;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.BookingApiResponse;
import com.ifazig.optdesk.api_model.ValidBookingModel;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {
    private final Context context;
    private final List<ValidBookingModel.Book> data;
    private final String starttime;
    private final String endtime;

    public TimeSlotAdapter(Context context, List<ValidBookingModel.Book> data, String starttime, String endtime) {
        this.context = context;
        this.data = data;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    @NonNull
    @Override
    public TimeSlotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_time_slot_list, parent, false);
        return new TimeSlotAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final TimeSlotAdapter.ViewHolder holder, int position) {
        ValidBookingModel.Book response = data.get(position);
        holder.txtDate.setText("These times are available for" + "\n" + response.getStartDate());
        holder.txtFromTime.setText(response.getStartTime());
        holder.txtToTime.setText(response.getEndTime());
        holder.lyoutFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeDialogfromTime(holder.txtFromTime, response);
            }
        });
        holder.lyoutToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeDialogtoTime(holder.txtToTime, response);
            }
        });
    }

    private void timeDialogtoTime(TextView txtToTime, ValidBookingModel.Book response) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

// Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                String strH = "", strM = "";
                if (hourOfDay < 10) {
                    strH = "0" + hourOfDay;
                } else {
                    strH = hourOfDay + "";
                }

// Mint
                if (minute < 10) {
                    strM = "0" + minute;
                } else {
                    strM = minute + "";
                }
                String selectedTime = strH + ":" + strM ;

               /* String ampm = "";
                if (hourOfDay < 12) {
                    ampm = "AM";
                } else {
                    hourOfDay = hourOfDay - 12;
                    ampm = "PM";
                }

// hour
                String strHs = "";
                if (hourOfDay == 0) {
                    hourOfDay = 12;
                }
                if (hourOfDay < 10) {
                    strHs = "0" + hourOfDay;
                } else {
                    strHs = hourOfDay + "";
                }*/

// txtTime.setText(hourOfDay + ":" + minute);

                //String time = (strHs + ":" + strM + " " + ampm);
                String time = (selectedTime);
                txtToTime.setText(time);
                response.setEndTime(time);
                CommonFunctions.ENDTIME = txtToTime.getText().toString();

            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void timeDialogfromTime(TextView txtFromTime, ValidBookingModel.Book response) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

// Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                String strH = "", strM = "";
                if (hourOfDay < 10) {
                    strH = "0" + hourOfDay;
                } else {
                    strH = hourOfDay + "";
                }

// Mint
                if (minute < 10) {
                    strM = "0" + minute;
                } else {
                    strM = minute + "";
                }
                String selectedTime = strH + ":" + strM ;

              /*  String ampm = "";
                if (hourOfDay < 12) {
                    ampm = "AM";
                } else {
                    hourOfDay = hourOfDay - 12;
                    ampm = "PM";
                }

// hour
                String strHs = "";
                if (hourOfDay == 0) {
                    hourOfDay = 12;
                }
                if (hourOfDay < 10) {
                    strHs = "0" + hourOfDay;
                } else {
                    strHs = hourOfDay + "";
                }*/

// txtTime.setText(hourOfDay + ":" + minute);

                //String time = (strHs + ":" + strM + " " + ampm);
                String time = (selectedTime);
                txtFromTime.setText(time);
                response.setStartTime(time);
                CommonFunctions.STARTTIME = txtFromTime.getText().toString();

            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtDate, txtFromTime, txtToTime;
        LinearLayout lyoutFromTime, lyoutToTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtFromTime = (TextView) itemView.findViewById(R.id.txtFromTime);
            txtToTime = (TextView) itemView.findViewById(R.id.txtToTime);
            lyoutFromTime = (LinearLayout) itemView.findViewById(R.id.lyoutFromTime);
            lyoutToTime = (LinearLayout) itemView.findViewById(R.id.lyoutToTime);
        }
    }
}