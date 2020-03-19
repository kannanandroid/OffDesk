package com.ifazig.optdesk.utils;

import android.content.Context;

/**
 * Created by Kannan on 12/08/2019.
 */
public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    SimpleArcDialog mDialog;
    ArcConfiguration mArcConfig;
    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }
    private CustomProgressDialog() {

    }

    public void show(Context mContext, String Title, String Msg) {
        if (mDialog != null)
            mDialog.dismiss();
        mDialog = new SimpleArcDialog(mContext);
        mArcConfig = new ArcConfiguration(mContext);
        mDialog.setTitle(Title);
        mArcConfig.setText (Msg);
        mDialog.setCancelable (false);
        //mDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation1;
        mDialog.setConfiguration(mArcConfig);
        mDialog.show();

    }
    public void dismiss() {
        if (mDialog != null)
            mDialog.dismiss();
    }
    public boolean isShowing() {
        if (mDialog != null && mDialog.isShowing())
            return true;
        else return false;
    }
}
