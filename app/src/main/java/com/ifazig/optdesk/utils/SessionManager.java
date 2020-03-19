package com.ifazig.optdesk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kannan on 12,Aug,2019
 */
public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public void insertIntoPreference(Context context, String KEY, String value) {
        SharedPreferences pref = context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, Activity.MODE_PRIVATE);
        pref.edit().putString(KEY, value).apply();
    }


    public String getFromPreference(String KEY) {
        SharedPreferences prefs = MyApplication.context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, Activity.MODE_PRIVATE);
        return prefs.getString(KEY, "");
    }


    public void Logout() {
        SharedPreferences prefs = MyApplication.context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }


}
