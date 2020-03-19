package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.adapter.PastBookingAdapter;
import com.ifazig.optdesk.adapter.SeatBookingAdapter;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.BookingConfirmationApiResponse;
import com.ifazig.optdesk.api_model.BookingSuccessApiResponse;
import com.ifazig.optdesk.api_model.InsertDataModel;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.ActivityBookingDetailsBinding;
import com.ifazig.optdesk.databinding.ActivitySeatBookingBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeatBookingActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySeatBookingBinding binding;
    private String ITEMS;
    public List<MultiValidWorkStationApiResponse.ValidationWorkstation> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_booking);
        initView();
    }

    private void initView() {
        binding.tvTitle.setText(LanguageConstants.workstation);
        binding.imgBack.setOnClickListener(this);
        binding.rlSubmit.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("ITEMS") != null) {
                ITEMS = getIntent().getExtras().getString("ITEMS");
                if (ITEMS != null) {
                    Gson gson = new Gson();
                    Type listOfObjects2 = new TypeToken<List<MultiValidWorkStationApiResponse.ValidationWorkstation>>() {
                    }.getType();
                    items = gson.fromJson(ITEMS, listOfObjects2);
                    itemMethod(items);
                }
            }
        }
    }

    private void itemMethod(List<MultiValidWorkStationApiResponse.ValidationWorkstation> items) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeatBookingActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.layoutSeat.setLayoutManager(layoutManager);
        SeatBookingAdapter adapter = new SeatBookingAdapter(SeatBookingActivity.this, items);
        binding.layoutSeat.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.rlSubmit:
                InsertDataModel insertDataModel = new InsertDataModel();
                InsertDataModel.Book book = new InsertDataModel.Book();
                List<InsertDataModel.Book> bookList = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    for (int j = 0; j < items.get(i).getWorkstationlist().size(); j++) {
                        if (items.get(i).getWorkstationlist().get(j).getBookingStatus() == 2) {
                            book.setStartDate(items.get(i).getDate());
                            book.setStartTime(items.get(i).getStartTime());
                            book.setEndTime(items.get(i).getEndTime());
                            book.setWorkstationId(items.get(i).getWorkstationlist().get(j).getWorkstationId());
                            bookList.add(book);
                        }
                    }
                    insertDataModel.setBook(bookList);

                }
                insertDataModel.setWingId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WINGID)));
                insertDataModel.setRoleId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID)));
                insertDataModel.setUserId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID)));
                insertDataModel.setCompanyId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.COMPANYID)));
                insertDataModel.setLocationId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.LOCATIONID)));
                insertDataModel.setBuildingId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.BUILDINGID)));
                insertDataModel.setFloorId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.FLOORID)));
                Gson gson = new Gson();
                String body = gson.toJson(insertDataModel);
                System.out.println("Input ==> " + body);
                if (insertDataModel.getBook() != null && insertDataModel.getBook().size() > 0) {
                    CommonApiCalls.getInstance().getBookingWorkStation(SeatBookingActivity.this, body, new CommonCallback.Listener() {
                        @Override
                        public void onSuccess(Object object) {
                            BookingConfirmationApiResponse body = (BookingConfirmationApiResponse) object;
                            CommonFunctions.getInstance().successResponseToast(SeatBookingActivity.this, body.getMessage());
                            CommonFunctions.getInstance().newIntent(SeatBookingActivity.this, SuccessActivity.class, Bundle.EMPTY, true, true);
                        }

                        @Override
                        public void onFailure(String reason) {

                        }
                    });
                } else {
                    CommonFunctions.getInstance().validationInfoError(SeatBookingActivity.this, LanguageConstants.selectseatfirst);
                }
                break;
        }
    }
}
