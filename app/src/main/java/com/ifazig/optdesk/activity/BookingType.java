package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivityBookingTypeBinding;
import com.ifazig.optdesk.databinding.ActivityMybookingsBinding;
import com.ifazig.optdesk.utils.LanguageConstants;

public class BookingType extends AppCompatActivity implements View.OnClickListener {
    ActivityBookingTypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_type);
        initView();
    }

    private void initView() {
        binding.imgBack.setOnClickListener(this);
        binding.lvbulkbooking.setOnClickListener(this);
        binding.lvcurrentDay.setOnClickListener(this);
        binding.tvTitle.setText(LanguageConstants.bookingtype);
        binding.tvcurrentday.setText(LanguageConstants.currentDay);
        binding.tvbulkbooking.setText(LanguageConstants.bulkBooking);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.lvbulkbooking:
                break;
            case R.id.lvcurrentDay:
                break;
            default:
                break;
        }

    }
}
