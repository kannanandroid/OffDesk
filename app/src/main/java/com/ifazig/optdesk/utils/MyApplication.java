package com.ifazig.optdesk.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.valdesekamdem.library.mdtoast.MDToast;

@SuppressLint("Registered")
public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
        //LanguageConstants.getInstance().languageConstants();
        /*Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(CommonFunctions.DBName.toLowerCase() + ".realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);*/
    }

    public static void displayKnownError(String message) {
        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    public static void displayUnKnownError() {
        MDToast mdToast = MDToast.makeText(context, LanguageConstants.somethingWentWrong, Toast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    public static void displayKnownErrorSuccess(Context mContext, String message) {
        //CommonFunctions.getInstance().ShowSnackBar(mContext, message);
        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }

    public static void result(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }






}
