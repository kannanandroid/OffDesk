package com.ifazig.optdesk.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.ifazig.optdesk.BuildConfig;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.LoginApiResponseModel;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.ActivityLoginBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    private String versionName = BuildConfig.VERSION_NAME;

    public static List<LoginApiResponseModel.CompanyDetail> companyDetails;
    List<LoginApiResponseModel.BuildingDetail> buildingDetails;
    List<LoginApiResponseModel.FloorDetail> floorDetails;
    List<LoginApiResponseModel.WingsDetail> wingDetails;
    List<LoginApiResponseModel.LocationDetail> locationdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        // text set
        binding.edEmail.setHint(LanguageConstants.emailidUsername);
        binding.edPassword.setHint(LanguageConstants.password);
        binding.appTitle.setText(LanguageConstants.optDesk);
        binding.btnSignin.setText(LanguageConstants.login);
        binding.forgotpasswordTitle.setText(LanguageConstants.forgotPassword);
        binding.txtRegister.setText(LanguageConstants.registera);
        binding.txtNewAccount.setText(LanguageConstants.newaccount);
        binding.rememberme.setText(LanguageConstants.rememberme);

        // listeners
        binding.llSignin.setOnClickListener(this);
        binding.forgotpasswordTitle.setOnClickListener(this);
        binding.lyoutNewAccount.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        Date d = new Date();
        CharSequence ddd = DateFormat.format("MMM d, yyyy ", d.getTime());
        //String pwdby = "\u00A9" + year + ". All rights reserved.\n \t\t\t  www.ifazig.com \n \t\t\t\t Version " + versionName;
        String ss = "Version" + versionName + "\n \t Release Date: " + "Mar4, 2020" + " \n \t  www.ifazig.com \n" + "\u00A9" + year + ". All rights reserved.";
        binding.tvcopyrights.setText(ss);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llSignin:
                if (binding.edEmail.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(LoginActivity.this, LanguageConstants.emailcantblank);
                    return;
                }
                /*if (!CommonFunctions.getInstance().isValidEmail(binding.edEmail.getText().toString().trim())) {
                    CommonFunctions.getInstance().validationInfoError(LoginActivity.this, LanguageConstants.invaildEmailFormat);
                    return;
                }*/
                if (binding.edPassword.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(LoginActivity.this, LanguageConstants.passwordcantblank);
                    return;
                }
                // call login api
                callLoginApi(binding.edEmail.getText().toString().trim(), binding.edPassword.getText().toString().trim());
                break;
            case R.id.forgotpasswordTitle:
                CommonFunctions.getInstance().newIntent(LoginActivity.this, ForgotPassword.class, Bundle.EMPTY, false, false);
                break;
            case R.id.lyoutNewAccount:
                CommonFunctions.getInstance().newIntent(LoginActivity.this, RegisterActivity.class, Bundle.EMPTY, false, false);
                break;

            default:
                break;
        }
    }

    /**
     * @param email
     * @param password
     */
    private void callLoginApi(String email, String password) {
        CommonApiCalls.getInstance().getLoginDetails(LoginActivity.this, email,
                password, "0", "0", "0", "0", "0", "0", "0", new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        LoginApiResponseModel body = (LoginApiResponseModel) object;
                        LoginApiResponseModel.ReturnData data = body.getReturnData();
                        if (body.getStatus()) {
                            if (body.getReturnData() != null && body.getReturnData().getUserDetails() != null) {
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.FIRST_NAME, data.getUserDetails().get(0).getUserFirstName());
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.LAST_NAME, data.getUserDetails().get(0).getUserLastName());
                                //SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.USER_NAME, data.getUserDetails().get(0).getUserName());
                                //SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.EMAIL, data.getUserDetails().get(0).getEmailID());
                                //SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.PHONE_NUMBER, data.getUserDetails().get(0).getMobileNo());
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.GROUPID, String.valueOf(data.getUserDetails().get(0).getGroupID()));
                                //SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.COMPANYID, String.valueOf(data.getUserDetails().get(0).getCompanyIDUser()));
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.USERID, String.valueOf(data.getUserDetails().get(0).getUserID()));
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.ROLEID, String.valueOf(data.getUserDetails().get(0).getRoleID()));
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.PROFILEPIC, data.getUserDetails().get(0).getCompLogo());
                                companyDetails = data.getCompanyDetails();
                                Gson gson = new Gson();
                                String companyDetail = gson.toJson(companyDetails);
                                SessionManager.getInstance().insertIntoPreference(LoginActivity.this, SharedPrefConstants.COMPANYDETAILS, companyDetail);
                                locationdetails = data.getLocationDetails();
                                buildingDetails = data.getBuildingDetails();
                                floorDetails = data.getFloorDetails();
                                wingDetails = data.getWingsDetails();
                                CommonFunctions.getInstance().newIntent(LoginActivity.this, HomeActivity.class, Bundle.EMPTY, true, true);
                            } else {
                                CommonFunctions.getInstance().successResponseToast(LoginActivity.this, LanguageConstants.usernamedosenotmatch);
                            }
                        } else {
                            CommonFunctions.getInstance().successResponseToast(LoginActivity.this, body.getMessage());
                        }


                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });

    }
}
