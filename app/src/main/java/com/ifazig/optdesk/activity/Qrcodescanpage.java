package com.ifazig.optdesk.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.ifazig.optdesk.BuildConfig;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api.CommonApiCalls;
import com.ifazig.optdesk.api_model.ScanBookingApiResponse;
import com.ifazig.optdesk.api_model.ScanBookingModel;
import com.ifazig.optdesk.api_model.WorkStationLogoutModel;
import com.ifazig.optdesk.api_model.WorkstationLogoutApiResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.ifazig.optdesk.databinding.QrcodescanpageBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import lolodev.permissionswrapper.callback.OnRequestPermissionsCallBack;
import lolodev.permissionswrapper.wrapper.PermissionWrapper;

public class Qrcodescanpage extends AppCompatActivity implements View.OnClickListener {
    QrcodescanpageBinding binding;
    public static final int WORK_STATION_REQUEST_CODE_QR = 601;
    // bunch of location related apis
    protected Location mLastLocation;
    FusedLocationProviderClient mFusedLocationClient;
    private String lat = "0.0";
    private String lng = "0.0";
    int REQUEST_PERMISSIONS_REQUEST_CODE = 1001;

    protected static final int REQUEST_CHECK_SETTINGS = 2020;
    protected static final String TAG = "Qrcodescanpage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.qrcodescanpage);
        binding.rvScanCode.setOnClickListener(this);
        binding.llSubmit.setOnClickListener(this);
        binding.ivLogout.setOnClickListener(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestlocation();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rvScanCode:
                permissionCheck();
                break;
            case R.id.ivLogout:
                WorkStationLogoutModel workStationLogoutModel = new WorkStationLogoutModel();
                workStationLogoutModel.setUserId(Integer.valueOf(SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID)));
                workStationLogoutModel.setIsLogin(0);
                Gson gson = new Gson();
                String body = gson.toJson(workStationLogoutModel);
                System.out.println("Input ==> " + body);
                CommonApiCalls.getInstance().getWorkStationLogoutApi(Qrcodescanpage.this, body, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        WorkstationLogoutApiResponse body = (WorkstationLogoutApiResponse) object;
                        CommonFunctions.getInstance().successResponseToast(Qrcodescanpage.this, body.getMessage());
                        CommonFunctions.getInstance().newIntent(Qrcodescanpage.this, LoginActivity.class, Bundle.EMPTY, true, true);
                    }

                    @Override
                    public void onFailure(String reason) {

                    }
                });

                break;
            case R.id.llSubmit:
                if (binding.tvTicketNo.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationEmptyError(Qrcodescanpage.this, LanguageConstants.qridcantblank);
                    return;
                }
                permissionCheckNew();
                break;
            default:
                break;
        }
    }

    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(6000);
        locationRequest.setFastestInterval(1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(Qrcodescanpage.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }


    private void permissionCheckNew() {
        new PermissionWrapper.Builder(this)
                .addPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA})
                //enable rationale message with a custom message
                .addPermissionRationale(LanguageConstants.needpermission)
                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosen
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (locationManager != null) {
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                displayLocationSettingsRequest(Qrcodescanpage.this);
                            } else {
                                if (!checkPermissions()) {
                                    requestPermissions();
                                } else {
                                    getLastLocationNew(binding.tvTicketNo.getText().toString().trim());
                                }


                            }
                        }

                    }

                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(Qrcodescanpage.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).build().request();

    }


    private void permissionCheck() {
        new PermissionWrapper.Builder(this)
                .addPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA})
                //enable rationale message with a custom message
                .addPermissionRationale(LanguageConstants.needpermission)
                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosen
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        if (locationManager != null) {
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                                displayLocationSettingsRequest(Qrcodescanpage.this);
                            } else {
                                if (!checkPermissions()) {
                                    requestPermissions();
                                } else {
                                    getLastLocation();
                                }

                            }
                        }

                    }

                    @Override
                    public void onDenied(String permission) {
                        Toast.makeText(Qrcodescanpage.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).build().request();
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();
                            lat = mLastLocation.getLatitude() + "";
                            lng = mLastLocation.getLongitude() + "";
                            CommonFunctions.getInstance().newIntentWithResult(Qrcodescanpage.this, DecoderActivity.class, Bundle.EMPTY, WORK_STATION_REQUEST_CODE_QR);
                        } else {
                            CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, "No Location found");
                            requestlocation();
                        }
                    }
                });
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocationNew(String qrvalue) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();
                            lat = mLastLocation.getLatitude() + "";
                            lng = mLastLocation.getLongitude() + "";
                            qrcodeApiCall(qrvalue);
                        } else {
                            CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, "No Location found");
                            requestlocation();
                        }
                    }
                });
    }
    /*private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }*/

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static float getKmFromLatLong(double lat1, double lng1, double lat2, double lng2) {
        Location loc1 = new Location("");
        loc1.setLatitude(lat1);
        loc1.setLongitude(lng1);
        Location loc2 = new Location("");
        loc2.setLatitude(lat2);
        loc2.setLongitude(lng2);
        float distanceInMeters = loc1.distanceTo(loc2);
        return distanceInMeters;
    }

    public static double distanceCalu(double lat1, double lat2, double lon1,
                                      double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * @param qrvalue
     */
    private void qrcodeApiCall(String qrvalue) {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mLastLocation = task.getResult();
                            lat = mLastLocation.getLatitude() + "";
                            lng = mLastLocation.getLongitude() + "";
                            float distan = getKmFromLatLong(mLastLocation.getLatitude(), mLastLocation.getLongitude(), Double.parseDouble(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WORKSTLATITUTE)), Double.parseDouble(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WORKSTLONGITUTE)));
                            //Toast.makeText(Qrcodescanpage.this, ""+cool, Toast.LENGTH_SHORT).show();
                            if (distan <= 500.00) {
                                qrcodeApiCallNew(qrvalue);
                            } else {
                                CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, LanguageConstants.outofworkstation);
                            }
                        } else {
                            CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, "No Location found");
                        }
                    }
                });

    }

    private void qrcodeApiCallNew(String qrvalue) {
        ScanBookingModel scanBookingModel = new ScanBookingModel();
        scanBookingModel.setFloorMapBookingId(qrvalue);
        scanBookingModel.setLatitude(lat);
        scanBookingModel.setLongitude(lng);
        scanBookingModel.setActualLatitude(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WORKSTLATITUTE));
        scanBookingModel.setActualLongitude(SessionManager.getInstance().getFromPreference(SharedPrefConstants.WORKSTLONGITUTE));
        scanBookingModel.setUserId(SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID));
        //Toast.makeText(this, lat + " " + lng, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        String body = gson.toJson(scanBookingModel);
        System.out.println("Input ==> " + body);
        CommonApiCalls.getInstance().getscanbookingApi(Qrcodescanpage.this, body, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object object) {
                ScanBookingApiResponse body = (ScanBookingApiResponse) object;
                binding.tvTicketNo.setText("");
                CommonFunctions.getInstance().successResponseToast(Qrcodescanpage.this, body.getMessage());
                CommonFunctions.getInstance().newIntent(Qrcodescanpage.this, SuccessAcitivity.class, Bundle.EMPTY, false, false);
            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }

    private void requestlocation() {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(6000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        lat = location.getLatitude() + "";
                        lng = location.getLongitude() + "";
                    }
                }
            }
        };
        LocationServices.getFusedLocationProviderClient(Qrcodescanpage.this).requestLocationUpdates(mLocationRequest, mLocationCallback, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case WORK_STATION_REQUEST_CODE_QR:
                if (resultCode == RESULT_OK) {
                    String requiredValue = data.getStringExtra("QRKEY");
                    if (requiredValue != null && !requiredValue.isEmpty()) {
                        qrcodeApiCall(requiredValue);
                    } else {
                        CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, LanguageConstants.qrcodenotempty);
                    }
                }
                break;
            case REQUEST_CHECK_SETTINGS:
                if (resultCode == RESULT_OK) {
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                    mFusedLocationClient.getLastLocation()
                            .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task) {
                                    if (task.isSuccessful() && task.getResult() != null) {
                                        mLastLocation = task.getResult();
                                        lat = mLastLocation.getLatitude() + "";
                                        lng = mLastLocation.getLongitude() + "";
                                    } else {
                                        CommonFunctions.getInstance().validationInfoError(Qrcodescanpage.this, "No Location found");
                                        requestlocation();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(this, "User denied to access location", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {

            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            } else {
                Intent intent = new Intent();
                intent.setAction(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",
                        BuildConfig.APPLICATION_ID, null);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

    }
}
