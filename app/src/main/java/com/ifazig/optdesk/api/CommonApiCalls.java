package com.ifazig.optdesk.api;

import android.content.Context;

import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;
import com.ifazig.optdesk.api_model.BookingSuccessApiResponse;
import com.ifazig.optdesk.api_model.CancelApiResponse;
import com.ifazig.optdesk.api_model.InsertFeedbcakApiResponse;
import com.ifazig.optdesk.api_model.LoginApiResponseModel;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;
import com.ifazig.optdesk.api_model.SettingsDetailsApiResponse;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.CustomProgressDialog;
import com.ifazig.optdesk.utils.MyApplication;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonApiCalls {
    private static CommonApiCalls ourInstance;

    public static CommonApiCalls getInstance() {
        ourInstance = new CommonApiCalls();
        return ourInstance;
    }

    /**
     * @param context
     * @param emailid
     * @param password
     * @param listener
     */
    public void getLoginDetails(final Context context, String emailid, String password, String UserId, String CompanyId, String RoleId, String LocationId, String BuildingId, String FloorId, String WingId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<LoginApiResponseModel> call = apiInterface.login(emailid, password, UserId, CompanyId, RoleId, LocationId, BuildingId, FloorId, WingId);
        call.enqueue(new Callback<LoginApiResponseModel>() {
            @Override
            public void onResponse(Call<LoginApiResponseModel> call, Response<LoginApiResponseModel> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<LoginApiResponseModel> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void getSettingssDetails(final Context context, String CompanyId, String LocationId, String UserId, String RoleId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<SettingsDetailsApiResponse> call = apiInterface.settingsDetails(CompanyId, LocationId, UserId, RoleId);
        call.enqueue(new Callback<SettingsDetailsApiResponse>() {
            @Override
            public void onResponse(Call<SettingsDetailsApiResponse> call, Response<SettingsDetailsApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<SettingsDetailsApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void getvalidWorkStation(final Context context, String body, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        RequestBody body_ = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<MultiValidWorkStationApiResponse> call = apiInterface.validWorkStation(body_);
        call.enqueue(new Callback<MultiValidWorkStationApiResponse>() {
            @Override
            public void onResponse(Call<MultiValidWorkStationApiResponse> call, Response<MultiValidWorkStationApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<MultiValidWorkStationApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void getBookingWorkStation(final Context context, String body, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        RequestBody body_ = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<BookingSuccessApiResponse> call = apiInterface.bookingWorkStation(body_);
        call.enqueue(new Callback<BookingSuccessApiResponse>() {
            @Override
            public void onResponse(Call<BookingSuccessApiResponse> call, Response<BookingSuccessApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<BookingSuccessApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void getBookingshistory(final Context context, String UserId, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<BookingHistoryApiResponse> call = apiInterface.bookinghistory(UserId);
        call.enqueue(new Callback<BookingHistoryApiResponse>() {
            @Override
            public void onResponse(Call<BookingHistoryApiResponse> call, Response<BookingHistoryApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }

    public void getcancelBooking(final Context context, Integer FloorMapBookingId, Integer IsCancel, final CommonCallback.Listener listener) {

        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context, "", "");
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<CancelApiResponse> call = apiInterface.cancelBooking(FloorMapBookingId, IsCancel);
        call.enqueue(new Callback<CancelApiResponse>() {
            @Override
            public void onResponse(Call<CancelApiResponse> call, Response<CancelApiResponse> response) {
                CustomProgressDialog.getInstance().dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        listener.onSuccess(response.body());
                    } else {
                        MyApplication.displayKnownError(response.body().getMessage());
                        listener.onFailure(response.body().getMessage());
                    }
                } else {
                    CommonFunctions.getInstance().apiError(context, response.errorBody(), listener);
                }
            }

            @Override
            public void onFailure(Call<CancelApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
                MyApplication.displayUnKnownError();
            }
        });
    }


}
