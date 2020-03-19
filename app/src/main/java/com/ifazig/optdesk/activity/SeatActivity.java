package com.ifazig.optdesk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.BookingSuccessApiResponse;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeatActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;


    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 150;
    int seatGaping = 10;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    String selectedIds = "";
    private ImageView imgBack;
    private TextView tvTitle;
    private String ITEMS;
    public List<ValidWorkStationApiResponse.ValidationWorkstation> items;
    private RelativeLayout rlSubmit;
    private String starttime;
    private String endtime;
    private String selecteddate;
    private String selectedIDLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_layout);

        layout = findViewById(R.id.layoutSeat);
        imgBack = (ImageView) findViewById(R.id.imgBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        rlSubmit = (RelativeLayout) findViewById(R.id.rlSubmit);
        tvTitle.setText(LanguageConstants.workstation);
        imgBack.setOnClickListener(this);
        rlSubmit.setOnClickListener(this);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("STARTTIME") != null) {
                starttime = getIntent().getExtras().getString("STARTTIME");
            }
            if (getIntent().getExtras().getString("ENDTIME") != null) {
                endtime = getIntent().getExtras().getString("ENDTIME");
            }
            if (getIntent().getExtras().getString("SELECTEDDATE") != null) {
                selecteddate = getIntent().getExtras().getString("SELECTEDDATE");
            }
            if (getIntent().getExtras().getString("ITEMS") != null) {
                ITEMS = getIntent().getExtras().getString("ITEMS");
                if (ITEMS != null) {
                    Gson gson = new Gson();
                    Type listOfObjects2 = new TypeToken<List<ValidWorkStationApiResponse.ValidationWorkstation>>() {
                    }.getType();
                    items = gson.fromJson(ITEMS, listOfObjects2);
                    itemMethod(items);
                }
            }
        }


    }

    private void itemMethod(List<ValidWorkStationApiResponse.ValidationWorkstation> items) {
        if (items != null && items.size() > 0) {
            LinearLayout layoutSeat = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutSeat.setOrientation(LinearLayout.VERTICAL);
            layoutSeat.setLayoutParams(params);
            layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
            layout.addView(layoutSeat);

            LinearLayout layout = null;

            int count = 0;

            for (int index = 0; index < items.size(); index++) {
                if (index == 0) {
                    layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layoutSeat.addView(layout);
                }
                if (items.get(index).getBookingStatus() == '/') {
                    layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    layoutSeat.addView(layout);
                } else if (items.get(index).getBookingStatus() == 1) {
                    count++;
                    TextView view = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                    view.setLayoutParams(layoutParams);
                    view.setPadding(0, 0, 0, 2 * seatGaping);
                    view.setId(items.get(index).getWorkstationId());
                    view.setGravity(Gravity.CENTER);
                    view.setBackgroundResource(R.drawable.ic_brown);
                    view.setTextColor(Color.BLACK);
                    view.setTag(STATUS_BOOKED);
                    view.setText(items.get(index).getWorkstationName());
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                    layout.addView(view);
                    seatViewList.add(view);
                    view.setOnClickListener(this);
                } else if (items.get(index).getBookingStatus() == 0) {
                    count++;
                    TextView view = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                    view.setLayoutParams(layoutParams);
                    view.setPadding(0, 0, 0, 2 * seatGaping);
                    view.setId(items.get(index).getWorkstationId());
                    view.setGravity(Gravity.CENTER);
                    view.setBackgroundResource(R.drawable.ic_white);
                    view.setText(items.get(index).getWorkstationName());
                    view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 9);
                    view.setTextColor(Color.BLACK);
                    view.setTag(STATUS_AVAILABLE);
                    layout.addView(view);
                    seatViewList.add(view);
                    view.setOnClickListener(this);
                } else if (items.get(index).getBookingStatus() == '_') {
                    TextView view = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                    layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                    view.setLayoutParams(layoutParams);
                    view.setBackgroundColor(Color.TRANSPARENT);
                    view.setText("");
                    layout.addView(view);
                }
            }

        } else {
            CommonFunctions.getInstance().validationEmptyError(SeatActivity.this, LanguageConstants.noDataFound);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() != null) {
            if ((int) view.getTag() == STATUS_AVAILABLE) {
                /*if (selectedIds.contains(view.getId() + ",")) {
                    selectedIds = selectedIds.replace(+view.getId() + ",", "");
                    view.setBackgroundResource(R.drawable.ic_white);
                } else {
                    selectedIds = selectedIds + view.getId() + ",";
                    view.setBackgroundResource(R.drawable.ic_yellow);
                }*/
                if (selectedIds.contains(view.getId() + ",")) {
                    selectedIds = selectedIds.replace(+view.getId() + ",", "");
                    view.setBackgroundResource(R.drawable.ic_white);
                } else {
                    selectedIds = selectedIds + view.getId() + ",";
                    view.setBackgroundResource(R.drawable.ic_yellow);
                }
                String[] split = selectedIds.split(",");
                int lenth = split.length;
                selectedIDLast = split[lenth - 1];
            } else if ((int) view.getTag() == STATUS_BOOKED) {
                Toast.makeText(this, "Seat is Already Booked", Toast.LENGTH_SHORT).show();
            }
        } else if (view == imgBack) {
            finish();
        } else if (view == rlSubmit) {
           /* if (selectedIDLast != null && !selectedIDLast.isEmpty()) {
                CommonApiCalls.getInstance().getBookingWorkStation(SeatActivity.this, Integer.parseInt(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WINGID)), Integer.valueOf(selectedIDLast.trim()), SessionManager.getInstance().getFromPreference(SharedPrefConstants.SELETEDDATE), SessionManager.getInstance().getFromPreference(SharedPrefConstants.SELETEDDATE), starttime, endtime, Integer.parseInt(SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID)),
                        Integer.parseInt(SessionManager.getInstance().getFromPreference(SharedPrefConstants.ROLEID)), new CommonCallback.Listener() {
                            @Override
                            public void onSuccess(Object object) {
                                BookingSuccessApiResponse body = (BookingSuccessApiResponse) object;
                                CommonFunctions.getInstance().successResponseToast(SeatActivity.this, body.getMessage());
                                CommonFunctions.getInstance().newIntent(SeatActivity.this, SuccessActivity.class, Bundle.EMPTY, true, true);
                            }

                            @Override
                            public void onFailure(String reason) {

                            }
                        });
            } else {
                CommonFunctions.getInstance().validationInfoError(SeatActivity.this, LanguageConstants.selectseatfirst);
            }*/
        }
    }
}
