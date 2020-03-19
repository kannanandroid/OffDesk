package com.ifazig.optdesk.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.adapter.TimeSlotAdapter;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;
import com.ifazig.optdesk.api_model.ValidBookingModel;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.ActivityTimeSlotBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.lang.reflect.Type;
import java.util.List;


public class TimeSlotActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityTimeSlotBinding binding;
    private String data;
    private String starttime = "";
    private String endtime = "";
    String selecteddate = "";
    private String selectedstarttime;
    private String selectedendtime;
    public List<ValidBookingModel.Book> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_time_slot);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("DATA") != null) {
                data = getIntent().getExtras().getString("DATA");
                if (data != null) {
                    Gson gson = new Gson();
                    Type listOfObjects2 = new TypeToken<List<ValidBookingModel.Book>>() {
                    }.getType();
                    items = gson.fromJson(data, listOfObjects2);
                    if (items != null && items.size() > 0) {
                        loadList(items);
                    }
                }
            }
            if (getIntent().getExtras().getString("STARTTIME") != null) {
                starttime = getIntent().getExtras().getString("STARTTIME");
            }
            if (getIntent().getExtras().getString("ENDTIME") != null) {
                endtime = getIntent().getExtras().getString("ENDTIME");
            }
            if (getIntent().getExtras().getString("SELECTEDDATE") != null) {
                selecteddate = getIntent().getExtras().getString("SELECTEDDATE");
            }

        }
        initView();
    }

    private void initView() {
        binding.imgBack.setOnClickListener(this);
        binding.tvTitle.setText(LanguageConstants.selectTimeSlot);
        binding.tvNoDateMessage.setText(LanguageConstants.noDataFound);
        binding.tvsubmit.setText(LanguageConstants.continuetxt);
        binding.rlSubmit.setOnClickListener(this);
        CommonFunctions.STARTTIME = "";
        CommonFunctions.ENDTIME = "";

    }


    private void loadList(List<ValidBookingModel.Book> data) {
        if (data != null && data.size() > 0) {
            binding.rvTime.setVisibility(View.VISIBLE);
            binding.llNoContentViewlayout.setVisibility(View.GONE);
            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(TimeSlotActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvTime.setLayoutManager(layoutManager);
            TimeSlotAdapter adapter = new TimeSlotAdapter(TimeSlotActivity.this, data, starttime, endtime);
            binding.rvTime.setAdapter(adapter);
        } else {
            binding.rvTime.setVisibility(View.GONE);
            binding.llNoContentViewlayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.rlSubmit:
                if (items != null && items.size() > 0) {
                    ValidBookingModel validBookingModel = new ValidBookingModel();
                    validBookingModel.setWingid(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WINGID));
                    validBookingModel.setRoleId(SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID));
                    validBookingModel.setUserId(SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID));
                    validBookingModel.setBook(items);
                    Gson gson = new Gson();
                    String body = gson.toJson(validBookingModel);
                    System.out.println("Input ==> " + body);
                    callTimeslotvalidApiCall(body);
                }
                break;
            default:
                break;

        }
    }

    private void callTimeslotvalidApiCall(String body) {
        CommonApiCalls.getInstance().getvalidWorkStation(TimeSlotActivity.this, body, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object object) {
                MultiValidWorkStationApiResponse body = (MultiValidWorkStationApiResponse) object;
                MultiValidWorkStationApiResponse.ReturnData data = body.getReturnData();
                if (data != null && data.getValidationWorkstation().size() > 0) {
                    Gson gson = new Gson();
                    String datas = gson.toJson(data.getValidationWorkstation());
                    Bundle bundle = new Bundle();
                    bundle.putString("ITEMS", datas);
                    CommonFunctions.getInstance().newIntent(TimeSlotActivity.this, SeatBookingActivity.class, bundle, false, false);
                } else {
                    CommonFunctions.getInstance().validationInfoError(TimeSlotActivity.this, LanguageConstants.noDataFound);
                }

            }

            @Override
            public void onFailure(String reason) {

            }
        });

    }
}
