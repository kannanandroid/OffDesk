package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivityRegisterBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initView();
    }

    private void initView() {
        binding.appTitle.setText(LanguageConstants.optDesk);
        binding.btnSubmit.setText(LanguageConstants.submit);
        binding.tvTitle.setText(LanguageConstants.registeraccount);
        binding.txtDoyouaccount.setText(LanguageConstants.doyouhacaccount);
        binding.txtLogin.setText(LanguageConstants.login);

        binding.edEmail.setHint(LanguageConstants.emailid);
        binding.edMobileNumber.setHint(LanguageConstants.mobilenumber);

        binding.llSubmit.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.lyoutNewAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.llSubmit:
                CommonFunctions.getInstance().newIntent(RegisterActivity.this, OtpActivity.class, Bundle.EMPTY, false, false);
                break;
            case R.id.lyoutNewAccount:
                finish();
                break;
            default:
                break;
        }
    }
}
