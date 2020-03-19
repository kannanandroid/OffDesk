package com.ifazig.optdesk.api;


import com.ifazig.optdesk.api_model.BookingHistoryApiResponse;
import com.ifazig.optdesk.api_model.BookingSuccessApiResponse;
import com.ifazig.optdesk.api_model.CancelApiResponse;
import com.ifazig.optdesk.api_model.FileuploadApiResponse;
import com.ifazig.optdesk.api_model.InsertFeedbcakApiResponse;
import com.ifazig.optdesk.api_model.LoginApiResponseModel;
import com.ifazig.optdesk.api_model.MultiValidWorkStationApiResponse;
import com.ifazig.optdesk.api_model.SettingsDetailsApiResponse;
import com.ifazig.optdesk.api_model.ValidWorkStationApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET(Urls.LOGIN)
    Call<LoginApiResponseModel> login(@Query("username") String email,
                                      @Query("pwd") String password,
                                      @Query("UserId") String UserId,
                                      @Query("CompanyId") String CompanyId,
                                      @Query("RoleId") String RoleId,
                                      @Query("LocationId") String LocationId,
                                      @Query("BuildingId") String BuildingId,
                                      @Query("FloorId") String FloorId,
                                      @Query("WingId") String WingId);

    @GET(Urls.SETTINGDETAILS)
    Call<SettingsDetailsApiResponse> settingsDetails(@Query("companyid") String companyid,
                                                     @Query("locationid") String locationid,
                                                     @Query("UserId") String UserId,
                                                     @Query("RoleId") String RoleId);

    @POST(Urls.VALIDATEWORKSTATION)
    Call<MultiValidWorkStationApiResponse> validWorkStation(@Body RequestBody body);

    @POST(Urls.CONFIRMATIONBOOKING)
    Call<BookingSuccessApiResponse> confirmationbooking(@Body RequestBody body);

    @POST(Urls.BOOKINGWORKSTATION)
    Call<BookingSuccessApiResponse> bookingWorkStation(@Body RequestBody body);

    @GET(Urls.BOOKINGHISTORY)
    Call<BookingHistoryApiResponse> bookinghistory(@Query("UserId") String UserId);


    @POST(Urls.CANCELBOOKING)
    Call<CancelApiResponse> cancelBooking(@Query("FloorMapBookingId") Integer FloorMapBookingId,
                                          @Query("IsCancel") Integer IsCancel);

    @POST(Urls.INSERTFEEDBACK)
    Call<InsertFeedbcakApiResponse> insertFeedback(@Body RequestBody body);

    @Multipart
    @POST(Urls.DOCUMENTUPLOAD)
    Call<FileuploadApiResponse> uploadfile(@Part MultipartBody.Part profile_image);


}