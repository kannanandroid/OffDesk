package com.ifazig.optdesk.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.adapter.PastBookingAdapter;
import com.ifazig.optdesk.adapter.UpComingListAdapter;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.ActivityMybookingsBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.util.List;

import hari.bounceview.BounceView;

public class MyBookingsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMybookingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mybookings);

        initialView();
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bookinglistApi();
            }
        });
    }

    // ---- Initial View
    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);

        binding.tvTitle.setText(LanguageConstants.mybookings);
        binding.txtPastbookings.setText(LanguageConstants.passtbookins);
        binding.txtUpComing.setText(LanguageConstants.upcoming);
        binding.txtFoodCourtNoDataFound.setText(LanguageConstants.noDataFound);
        binding.txtShoppingNoDataFound.setText(LanguageConstants.noDataFound);

        binding.imgBack.setOnClickListener(this);
        binding.rlpastbooking.setOnClickListener(this);
        binding.rlyoutShopping.setOnClickListener(this);

        binding.txtPastbookings.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.colorPrimary));
        binding.viewPastbookings.setVisibility(View.VISIBLE);
        binding.lyoutFoodCourt.setVisibility(View.VISIBLE);

        binding.txtUpComing.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.gray));
        binding.viewShopping.setVisibility(View.GONE);
        binding.lyoutShopping.setVisibility(View.GONE);

        bookinglistApi();

    }

    // ---- OnClick
    @Override
    public void onClick(View view) {
        if (view == binding.imgBack) {
            finish();
        } else if (view == binding.rlpastbooking) {
            binding.txtPastbookings.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.colorPrimary));
            binding.viewPastbookings.setVisibility(View.VISIBLE);
            binding.lyoutFoodCourt.setVisibility(View.VISIBLE);

            binding.txtUpComing.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.gray));
            binding.viewShopping.setVisibility(View.GONE);
            binding.lyoutShopping.setVisibility(View.GONE);
        } else if (view == binding.rlyoutShopping) {
            binding.txtPastbookings.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.gray));
            binding.viewPastbookings.setVisibility(View.GONE);
            binding.lyoutFoodCourt.setVisibility(View.GONE);

            binding.txtUpComing.setTextColor(ContextCompat.getColor(MyBookingsActivity.this, R.color.colorPrimary));
            binding.viewShopping.setVisibility(View.VISIBLE);
            binding.lyoutShopping.setVisibility(View.VISIBLE);

        }
    }

    // ------ Food Court
    private void bookinglistApi() {
        CommonApiCalls.getInstance().getBookingshistory(MyBookingsActivity.this, SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID),
                new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        BookingHistoryApiResponse body = (BookingHistoryApiResponse) object;
                        BookingHistoryApiResponse.ReturnData data = body.getReturnData();
                        if (data != null && data.getPastBookingHistory() != null && data.getPastBookingHistory().size() > 0) {
                            loadList(data.getPastBookingHistory());
                        } else {
                            CommonFunctions.getInstance().validationInfoError(MyBookingsActivity.this, LanguageConstants.noDataFound);
                        }
                        if (data != null && data.getUpcomingBookingHistory() != null && data.getUpcomingBookingHistory().size() > 0) {
                            loadbookingHistoryList(data.getUpcomingBookingHistory());
                        } else {
                            CommonFunctions.getInstance().validationInfoError(MyBookingsActivity.this, LanguageConstants.noDataFound);
                        }

                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });


    }


    // ---- Load upcoming history Adapter
    private void loadbookingHistoryList(List<BookingHistoryApiResponse.UpcomingBookingHistory> data) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (data.size() > 0) {
            binding.rvShopping.setVisibility(View.VISIBLE);
            binding.txtShoppingNoDataFound.setVisibility(View.GONE);

            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(MyBookingsActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvShopping.setLayoutManager(layoutManager);
            UpComingListAdapter adapter = new UpComingListAdapter(MyBookingsActivity.this, data);
            binding.rvShopping.setAdapter(adapter);
        } else {
            binding.rvShopping.setVisibility(View.GONE);
            binding.txtShoppingNoDataFound.setVisibility(View.VISIBLE);
        }
    }

    // ---- Load PastBooking Adapter
    private void loadList(List<BookingHistoryApiResponse.PastBookingHistory> data) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (data.size() > 0) {
            binding.rvFoodCourt.setVisibility(View.VISIBLE);
            binding.txtFoodCourtNoDataFound.setVisibility(View.GONE);

            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(MyBookingsActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvFoodCourt.setLayoutManager(layoutManager);
            PastBookingAdapter adapter = new PastBookingAdapter(MyBookingsActivity.this, data);
            binding.rvFoodCourt.setAdapter(adapter);
        } else {
            binding.rvFoodCourt.setVisibility(View.GONE);
            binding.txtFoodCourtNoDataFound.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        bookinglistApi();
    }
}
