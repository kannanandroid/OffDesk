package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivityForgotPasswordBinding;
import com.ifazig.optdesk.utils.LanguageConstants;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        initview();
    }

    private void initview() {
        binding.imgBack.setOnClickListener(this);
        binding.llSubmit.setOnClickListener(this);
        binding.edEmail.setHint(LanguageConstants.emailid);
        binding.tvTitle.setText(LanguageConstants.forgotPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.llSubmit:
                finish();
                break;
            default:
                break;
        }
    }
}
