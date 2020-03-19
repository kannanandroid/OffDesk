package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;
import com.ifazig.optdesk.api_model.CancelApiResponse;
import com.ifazig.optdesk.api_model.LoginApiResponseModel;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.ActivityBookingDetailsBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.lang.reflect.Type;
import java.util.List;

public class BookingDetails extends AppCompatActivity implements View.OnClickListener {
    ActivityBookingDetailsBinding binding;
    private String ITEMS;
    private BookingHistoryApiResponse.UpcomingBookingHistory items;

    public static final int REQUEST_CODE_QR = 501;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details);
        binding.imgBack.setOnClickListener(this);
        binding.rlscannow.setOnClickListener(this);
        binding.cancelBooking.setOnClickListener(this);
        initView();
    }

    private void initView() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("ITEMS") != null) {
                ITEMS = getIntent().getExtras().getString("ITEMS");
                if (ITEMS != null) {
                    Gson gson = new Gson();
                    items = gson.fromJson(ITEMS, BookingHistoryApiResponse.UpcomingBookingHistory.class);
                    if (items != null) {
                        binding.txtLocationvalue.setText(items.getLocationName());
                        binding.txtBuildingValue.setText(items.getBuildingName());
                        binding.txtFloorvalue.setText(items.getFloorName());
                        binding.txtWingValue.setText(items.getWingName());
                        for (int j = 0; j < items.getBook().size(); j++) {
                            LinearLayout item = (LinearLayout) findViewById(R.id.inflateLayoutOnes);
                            View newView1 = getLayoutInflater().inflate(R.layout.inflate_layout_two, null);
                            TextView workstationTxt = (TextView) newView1.findViewById(R.id.txtWorkStation);
                            TextView txtWorkStationvalue = (TextView) newView1.findViewById(R.id.txtWorkStationvalue);
                            TextView startDate = (TextView) newView1.findViewById(R.id.startDate);
                            TextView startTime = (TextView) newView1.findViewById(R.id.startTime);
                            TextView endDate = (TextView) newView1.findViewById(R.id.endDate);
                            TextView endTime = (TextView) newView1.findViewById(R.id.endTime);
                            workstationTxt.setText(LanguageConstants.workstationtxt);
                            txtWorkStationvalue.setText(items.getBook().get(j).getWorkstationName());
                            startDate.setText(items.getBook().get(j).getDate());
                            startTime.setText(items.getBook().get(j).getStartTime());
                            endDate.setText(items.getBook().get(j).getDate());
                            endTime.setText(items.getBook().get(j).getToTime());
                            item.addView(newView1);
                        }
                    }

                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
            case R.id.cancelBooking:
                cancelalertDialog();
                break;
            case R.id.rlscannow:
                CommonFunctions.getInstance().newIntentWithResult(BookingDetails.this, DecoderActivity.class, Bundle.EMPTY, REQUEST_CODE_QR);
                break;
            default:
                break;
        }
    }

    // ---- LogOut Dialog
    private void cancelalertDialog() {
        final Dialog dialogLogOut = new Dialog(BookingDetails.this);
        dialogLogOut.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLogOut.setContentView(R.layout.dialog_logout);
        dialogLogOut.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogLogOut.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogLogOut.setCancelable(false);

        TextView txtMsg = (TextView) dialogLogOut.findViewById(R.id.txtMsg);
        TextView txtClose = (TextView) dialogLogOut.findViewById(R.id.txtClose);
        TextView txtOk = (TextView) dialogLogOut.findViewById(R.id.txtOk);

        txtMsg.setText(LanguageConstants.areYouSureWantToCancel);
        txtClose.setText(LanguageConstants.no);
        txtOk.setText(LanguageConstants.yes);

        dialogLogOut.show();

        // --- Close
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogOut.dismiss();
            }
        });

        // --- Ok
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogOut.dismiss();
                cancelandscanApi(items.getFloorMapBookingId(), 1);
            }
        });
    }

    private void cancelandscanApi(Integer floorMapBookingId, Integer s) {
        CommonApiCalls.getInstance().getcancelBooking(BookingDetails.this, floorMapBookingId, s,
                new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        CancelApiResponse body = (CancelApiResponse) object;
                        CommonFunctions.getInstance().successResponseToast(BookingDetails.this, body.getMessage());
                        finish();
                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case REQUEST_CODE_QR:
                    if (resultCode == RESULT_OK) {
                        String requiredValue = data.getStringExtra("QRKEY");
                        if (requiredValue != null && !requiredValue.isEmpty()) {
                            cancelandscanApi(Integer.valueOf(requiredValue.trim()), 0);
                        } else {
                            CommonFunctions.getInstance().validationInfoError(BookingDetails.this, LanguageConstants.qrcodenotempty);
                        }
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
