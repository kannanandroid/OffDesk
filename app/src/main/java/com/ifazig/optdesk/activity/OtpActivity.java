package com.ifazig.optdesk.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivityOtpBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;


public class OtpActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityOtpBinding binding;
    String phonenumber;
    private String otpuniquekey;
    private String strOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp);
        initView();
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("PHONENUMBER") != null) {
                phonenumber = getIntent().getExtras().getString("PHONENUMBER");
                otpuniquekey = getIntent().getExtras().getString("OTPUNIQUEKEY");
            }
        }
        binding.pinEntryEditOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                if (str.length() == 4) {
                    strOTP = str.toString();
                    CommonFunctions.getInstance().newIntent(OtpActivity.this, HomeActivity.class, Bundle.EMPTY, true, false);
                    //verfiyOTP(strOTP, otpuniquekey, phonenumber);
                }
            }
        });
    }

    /*private void verfiyOTP(String toString, String otpuniquekey, String phonenumber) {
        CommonApiCalls.getInstance().visitorVerifyOtpApi(OtpActivity.this, toString, otpuniquekey, phonenumber, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                VisitorVerifyOtpResponse apiResponse = (VisitorVerifyOtpResponse) body;
                CommonFunctions.getInstance().successResponseToast(OtpActivity.this, apiResponse.getMessage());
                if(apiResponse.getDetails()!=null) {
                    if(apiResponse.getDetails().getVisitorId().equals(0)) {
                        Bundle bundle = new Bundle();
                        bundle.putString("PHONENUMBER", phonenumber);
                        CommonFunctions.getInstance().newIntent(OtpActivity.this, UserRegisterActivity.class, bundle, true, false);
                    }else {
                        CommonFunctions.getInstance().successResponseToast(OtpActivity.this, apiResponse.getMessage());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_USER_ID, String.valueOf(apiResponse.getDetails().getVisitorId()));
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_FIRST_NAME, apiResponse.getDetails().getVisitorFirstName());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_LAST_NAME, apiResponse.getDetails().getVisitorLastName());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_EMAIL_ADDRESS, apiResponse.getDetails().getEmail());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_PROF_TYPE_ID, String.valueOf(apiResponse.getDetails().getIdProofId()));
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_PROF_NUMBER, apiResponse.getDetails().getIdProofNumber());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_MOBILE_NO, apiResponse.getDetails().getContactNumber());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_ADDRESS, apiResponse.getDetails().getAddress());
                        SessionManager.getInstance().insertIntoPreference(OtpActivity.this, SharedPrefConstants.VISITOR_PHOTO_PATH, apiResponse.getDetails().getPhotoPath());
                        CommonFunctions.getInstance().newIntent(OtpActivity.this, VisitorHomeActivity.class, Bundle.EMPTY, true, true);
                    }
                }

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(OtpActivity.this, reason);
                binding.pinEntryEditOtp.setText("");
            }
        });
    }*/

    private void initView() {
        binding.tvResendCode.setText(LanguageConstants.resendotp);
        binding.txtUserOtp.setText(LanguageConstants.enterotp);
        binding.tvResendCode.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.tvTitle.setText(LanguageConstants.verification);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvResendCode:
                /*binding.pinEntryEditOtp.setText("");
                CommonApiCalls.getInstance().visitorLoginApi(OtpActivity.this, phonenumber, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        VisitorLoginApiResponse apiResponse = (VisitorLoginApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(OtpActivity.this, apiResponse.getMessage());
                        otpuniquekey = apiResponse.getDetails().getOtpUniqueKey();
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(OtpActivity.this, reason);
                    }
                });*/
                break;
            case R.id.imgBack:
                finish();
                break;
            default:
                break;
        }

    }
}
