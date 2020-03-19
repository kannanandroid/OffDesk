package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivitySuccessBinding;
import com.ifazig.optdesk.utils.CommonFunctions;

public class SuccessActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_success);
        binding.rlSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlSubmit:
                CommonFunctions.getInstance().newIntent(SuccessActivity.this, HomeActivity.class, Bundle.EMPTY, true, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
