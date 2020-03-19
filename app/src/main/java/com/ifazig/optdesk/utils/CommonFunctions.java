package com.ifazig.optdesk.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ifazig.optdesk.R;
import com.ifazig.optdesk.api_model.ErrorResponse;
import com.ifazig.optdesk.callback.CommonCallback;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


public class CommonFunctions {
    public static String DBName = "SignalStrength_DB";
    private static CommonFunctions commonFunctionsInstance = new CommonFunctions();
    public static String STARTTIME = "";
    public static  String ENDTIME = "";

    public enum AddressListType {
        AddressList("AddressList", 1),
        PickUpAddress("PickupAddress", 2),
        DeliveryAddress("DeliveryAddress", 3);

        private String stringValue;
        private int intValue;

        private AddressListType(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        public static AddressListType fromInteger(int x) {
            switch (x) {
                case 1:
                    return AddressList;
                case 2:
                    return PickUpAddress;
                case 3:
                    return DeliveryAddress;

            }
            return null;
        }

        public int getValue() {
            return intValue;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }


    public static CommonFunctions getInstance() {
        return commonFunctionsInstance;
    }

    /**
     * @param context          Context fromactivity or fragment
     * @param bundle           Bundle of values for next Activity
     * @param destinationClass Destination Activity
     * @param isFinish         Current activity need to finish or not
     */
    public void newIntent(Context context, Class destinationClass, Bundle bundle, boolean isFinish, boolean isFlags) {
        if (!isActivityRunning(context)){
            return;
        }
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        if (isFlags) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    /**
     * @param context          Context fromactivity or fragment
     * @param bundle           Bundle of values for next Activity
     * @param destinationClass Destination Activity
     * @param requestCode
     */
    public void newIntentWithResult(Context context, Class destinationClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        ((AppCompatActivity) context).startActivityForResult(intent, requestCode);

    }

    /**
     * Format string
     *
     * @param text
     * @param replaceText
     * @return
     */
    public String messageFormatPattern(String text, String... replaceText) {
        String finalText = "";
        for (String aReplaceText : replaceText) {
            finalText = MessageFormat.format(text, aReplaceText);
        }
        return finalText;
    }


    // --- TYPE_INFO
    public void validationInfoError(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_INFO);
        mdToast.show();
    }

    // --- TYPE_ERROR
    public void validationEmptyError(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorError));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    // --- TYPE_SUCCESS
    public void successResponseToast(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorSuccess));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }

    public void newIntentForRelt(Context context, Class destinationClass, Bundle bundle, boolean isFinish, int requestCode) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
        if (isFinish) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ((Activity) context).finish();
        }
    }


    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidMobile(String phone) {
        boolean check;
        //  check = Pattern.matches("[0-9]{11,12}", phone) && !(phone.length() < 11 || phone.length() > 12);

        check = Pattern.matches("[0-9]{10,11}", phone) && !(phone.length() < 10 || phone.length() > 11);

        return check;

   /*     String pattern = "[60][0-9]{8,12}";
        if (phone.matches(pattern)){
            return  true;
        }
        return false;*/
    }


    public static boolean CheckInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }


    /**
     * Check activity is running or not
     *
     * @param ctx
     * @return
     */
    public boolean isActivityRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        assert activityManager != null;
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }

        return false;
    }


    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * Convert date format from one to another
     *
     * @param currentFormat
     * @param requiredFormat
     * @param dateString
     * @return
     */
    public String changeDateFormat(String currentFormat, String requiredFormat, String dateString) {
        String result = "";
        if (dateString == null || dateString.isEmpty()) {
            return result;
        }
        SimpleDateFormat formatterOld = new SimpleDateFormat(currentFormat, Locale.ENGLISH);
        SimpleDateFormat formatterNew = new SimpleDateFormat(requiredFormat, Locale.ENGLISH);
        Date date = null;
        try {
            date = formatterOld.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            result = formatterNew.format(date);
        }
        return result;
    }

    public void chaneTextColorBasedOnRestaurant(String colorCode, TextView... textViews) {
        for (int count = 0; count < textViews.length; count++) {
            textViews[count].setTextColor(Color.parseColor(colorCode));
        }

    }

    // This method return current date
    public String getCurrentDateTime() {
        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }

    //This method is used for getCurrent Month First date
    public String getCurrentMonthFirstDate() {
        String sdf = "MM-dd-yyyy";
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat df = new SimpleDateFormat(sdf);
        return df.format(c.getTime());
    }

    public void apiError(Context context, ResponseBody response, CommonCallback.Listener listener) {
        try {
            BufferedSource source = response.source();
            Buffer buffer = source.buffer();
            String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));

            Gson gson = new Gson();
            ErrorResponse errorResponse = gson.fromJson(responseBodyString, ErrorResponse.class);

            listener.onFailure(errorResponse.getMessage());
            MyApplication.displayKnownError(errorResponse.getMessage());
        } catch (IllegalStateException e) {
            listener.onFailure("");
            MyApplication.displayKnownError(LanguageConstants.somethingWentWrong);
        }
    }

    public void drawLayout(final AppCompatActivity activity, Toolbar toolbars) {
        final DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawerLayout);
        activity.setSupportActionBar(toolbars);
        toolbars.setNavigationIcon(R.drawable.ic_menu);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbars, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                inputMethodManager.hideSoftInputFromWindow(new View(activity).getWindowToken(), 0);
            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

}
