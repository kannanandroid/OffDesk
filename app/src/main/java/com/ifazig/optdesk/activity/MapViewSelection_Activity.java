package com.ifazig.optdesk.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.util.List;
import java.util.Locale;


public class MapViewSelection_Activity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    private GoogleMap mMap;

    private double longitude;
    private double latitude;
    private FusedLocationProviderClient mFusedLocationClient;

    private Button Bt_ok;


    private String[] radious = {"Select the radius limit", "100", "200", "300", "400",
            "500"};

    private Spinner sp_radious;

    Circle mapCircle;
    Marker marker;
    private Double radiousMeter = 0.0;
    private TextView tvaddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_selectiion_view);
        setTitle("Set Office Location");

        initUi();
    }


    private void initUi() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bt_ok = findViewById(R.id.Bt_ok);
        sp_radious = findViewById(R.id.sp_radious);
        tvaddress = (TextView)findViewById(R.id.tvaddress);


        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(MapViewSelection_Activity.this, R.layout.spinner_item, radious);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sp_radious.setAdapter(arrayAdapter);


        Bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude > 0 && longitude > 0 && radiousMeter > 1) {
                    conformalertDialog(latitude,longitude,radiousMeter);
                } else {
                    Toast.makeText(MapViewSelection_Activity.this, "First Select Radius Limit", Toast.LENGTH_SHORT).show();
                }

            }
        });


        sp_radious.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    LatLng latLng = new LatLng(latitude, longitude);

                    radiousMeter = Double.valueOf(parent.getAdapter().getItem(position).toString());

                    addCircle(latLng, radiousMeter);
                } else {
                    radiousMeter = 0.0;

                    if (mapCircle != null) {
                        mapCircle.remove();
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        LatLng latLng = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setOnMarkerDragListener(this);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        getCurrentLocation();

        moveMap();
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    // ---- confirm Dialog
    private void conformalertDialog(double latitude, double longitude, Double radious) {
        final Dialog dialogLogOut = new Dialog(MapViewSelection_Activity.this);
        dialogLogOut.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLogOut.setContentView(R.layout.dialog_logout);
        dialogLogOut.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogLogOut.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogLogOut.setCancelable(false);

        TextView txtMsg = (TextView) dialogLogOut.findViewById(R.id.txtMsg);
        TextView txtClose = (TextView) dialogLogOut.findViewById(R.id.txtClose);
        TextView txtOk = (TextView) dialogLogOut.findViewById(R.id.txtOk);

        txtMsg.setText(LanguageConstants.wsconfirmTxt);
        txtClose.setText(LanguageConstants.no);
        txtOk.setText(LanguageConstants.yes);

        dialogLogOut.show();

        // --- Close
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogOut.dismiss();
            }
        });

        // --- Ok
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogOut.dismiss();
                SessionManager.getInstance().insertIntoPreference(MapViewSelection_Activity.this, SharedPrefConstants.WORKSTLATITUTE, String.valueOf(latitude));
                SessionManager.getInstance().insertIntoPreference(MapViewSelection_Activity.this, SharedPrefConstants.WORKSTLONGITUTE, String.valueOf(longitude));
                SessionManager.getInstance().insertIntoPreference(MapViewSelection_Activity.this, SharedPrefConstants.RADIOUSMETER, String.valueOf(radious));
                CommonFunctions.getInstance().newIntent(MapViewSelection_Activity.this, Qrcodescanpage.class, Bundle.EMPTY, true, false);
            }
        });
    }
    private void addCircle(LatLng radiusLatLon, Double radius) {


        if (mapCircle != null) {
            mapCircle.remove();
        }

        mapCircle = mMap.addCircle(new CircleOptions()
                .center(radiusLatLon)
                .radius(radius)
                .strokeWidth(0f)
                .fillColor(0x550000FF));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    private void getCurrentLocation() {
        mMap.clear();
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations, this can be null.
                        if (location != null) {
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                            String str = getCompleteAddressString(latitude, longitude);
                            tvaddress.setText(str);
                            //moving the map to location
                            moveMap();
                        }
                    }
                });


    }

    private void moveMap() {
        //String to display current latitude and longitude
        String msg = latitude + ", " + longitude;

        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        if (marker != null) {
            marker.remove();
        }


        marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.
                fromBitmap(drawableToBitmap(ContextCompat.getDrawable(MapViewSelection_Activity.this,
                        R.drawable.pin))))
                .position(latLng)
                .draggable(true)
                .title("Your Position"));


        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //Displaying current coordinates in toast
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {


        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;


        if (mapCircle != null) {
            mapCircle.remove();

            sp_radious.setSelection(0);
        }

        //Moving the map
        moveMap();


    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        int height = 60;
        int width = 60;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {

                Bitmap b = bitmapDrawable.getBitmap();

                return Bitmap.createScaledBitmap(b, width, height, false);
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth() + 30, drawable.getIntrinsicHeight() + 30, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
