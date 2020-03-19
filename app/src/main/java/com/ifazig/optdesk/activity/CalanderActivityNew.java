package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.google.gson.Gson;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.ValidBookingModel;
import com.ifazig.optdesk.databinding.ActivityCalanderBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.DateConstants;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalanderActivityNew extends AppCompatActivity implements View.OnClickListener {
    ActivityCalanderBinding binding;
    ArrayList<String> strDate;
    private Integer maxhours;
    private String starttime;
    private String endtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calander);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("MAXHOURS") != 0) {
                maxhours = getIntent().getExtras().getInt("MAXHOURS");
            }
            if (getIntent().getExtras().getString("STARTTIME") != null) {
                starttime = getIntent().getExtras().getString("STARTTIME");
            }
            if (getIntent().getExtras().getString("ENDTIME") != null) {
                endtime = getIntent().getExtras().getString("ENDTIME");
            }
        }
        initView();
        strDate = new ArrayList<>();
        Calendar min = Calendar.getInstance();
        binding.calendarViewOne.setMinimumDate(min);
        try {
            binding.calendarViewOne.setDate(min);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        binding.calendarViewOne.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                /*app:selectionLabelColor="@color/white"
                app:selectionColor="@color/colp"*/

                Date dates_ = eventDay.getCalendar().getTime();
                SimpleDateFormat dateFormatLocal_temp = new SimpleDateFormat(DateConstants.DATE_FORMAT_T, Locale.ENGLISH);
                //String finalDate = CommonFunctions.getInstance().changeDateFormat(DateConstants.DATE_FORMAT_T, DateConstants.DATE_FORMAT_EEE_DD_MMM, dateFormatLocal_temp.format(dates_));
                String postDate = CommonFunctions.getInstance().changeDateFormat(DateConstants.DATE_FORMAT_T, DateConstants.DATE_FORMAT_YYYY_MM_DD, dateFormatLocal_temp.format(dates_));

                boolean isZero = false;
                if (strDate.size() > 0) {
                    for (int i = 0; i < strDate.size(); i++) {
                        if (postDate.equals(strDate.get(i))) {
                            strDate.remove(i);
                            isZero = true;
                            break;
                        } else {
                            // nothing
                        }
                    }
                }

                if (isZero) {
                    // nothing
                } else {
                    strDate.add(postDate);
                    //Toast.makeText(CalanderActivityNew.this, "" + strDate, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void initView() {
        binding.tvsubmit.setText(LanguageConstants.continuetxt);
        binding.tvTitle.setText(LanguageConstants.bookyourworkstation);
        binding.rlSubmit.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.tvmaxinumnber.setText("This user to allow maximam no of dates:" + " " + maxhours);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.rlSubmit:
                if (strDate != null && strDate.size() > 0) {
                    if (maxhours >= strDate.size()) {
                        ArrayList<ValidBookingModel.Book> books = new ArrayList<>();
                        for (int i = 0; i < strDate.size(); i++) {
                            ValidBookingModel.Book book = new ValidBookingModel.Book();
                            book.setStartDate(strDate.get(i));
                            book.setStartTime(starttime);
                            book.setEndTime(endtime);
                            books.add(book);
                        }
                        Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String data = gson.toJson(books);
                        bundle.putString("DATA", data);
                        bundle.putString("STARTTIME", starttime);
                        bundle.putString("ENDTIME", endtime);
                        CommonFunctions.getInstance().newIntent(CalanderActivityNew.this, TimeSlotActivity.class, bundle, false, false);
                    } else {
                        binding.tvmaxinumnber.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                        CommonFunctions.getInstance().validationEmptyError(CalanderActivityNew.this, "Maximum Date reached contact admin");

                    }

                } else {
                    CommonFunctions.getInstance().validationEmptyError(CalanderActivityNew.this, LanguageConstants.selectDateFirst);

                }
                break;
            default:
                break;
        }

    }
}
