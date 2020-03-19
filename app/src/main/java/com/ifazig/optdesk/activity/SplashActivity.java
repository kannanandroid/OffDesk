package com.ifazig.optdesk.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.ifazig.optdesk.R;
import com.ifazig.optdesk.databinding.ActivitySplashBinding;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;
import com.ifazig.optdesk.utils.VersionComparator;

import org.jsoup.Jsoup;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    long SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.vid_splash;
        binding.videoView.setVideoURI(Uri.parse(path));
        binding.videoView.start();
        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                LoadSplash();
            }
        });


    }

    void LoadSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new GetVersionCode().execute();
            }
        }, SPLASH_TIME);
    }

    private class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            try {

                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + SplashActivity.this.getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                e.printStackTrace();
                return newVersion;
            }
        }

        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            String currentVersion = null;
            try {
                currentVersion = SplashActivity.this.getPackageManager().getPackageInfo(SplashActivity.this.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (onlineVersion != null && currentVersion != null && !onlineVersion.isEmpty() && !currentVersion.isEmpty()) {

                int version = VersionComparator.compareVersionNames(currentVersion, onlineVersion);
                if (version < 0) {
                    ShowUpdateDialog();
                } else {
                    LoadNext();
                }
            } else {
                LoadNext();
            }
        }
    }


    private void ShowUpdateDialog() {
        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_updateapp);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);

        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
        Button btnLater = (Button) dialog.findViewById(R.id.btnLater);


        tvTitle.setText(LanguageConstants.NewVersion);
        tvMessage.setText(LanguageConstants.NewVersionMessage);
        btnUpdate.setText(LanguageConstants.Update);
        btnLater.setText(LanguageConstants.Later);
        btnLater.setVisibility(View.GONE);

        btnLater.setAllCaps(false);
        btnUpdate.setAllCaps(false);

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + SplashActivity.this.getPackageName()));
                startActivity(intent);
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        if (!SplashActivity.this.isFinishing()) {
            dialog.show();
        }

    }

    void LoadNext() {
        if (CommonFunctions.getInstance().isActivityRunning(SplashActivity.this)) {
            if (!SessionManager.getInstance().getFromPreference(SharedPrefConstants.USERID).trim().isEmpty()) {
                //CommonFunctions.getInstance().newIntent(SplashActivity.this, HomeActivity.class, Bundle.EMPTY, true);
                CommonFunctions.getInstance().newIntent(SplashActivity.this, LoginActivity.class, Bundle.EMPTY, true,true);
            } else {
                CommonFunctions.getInstance().newIntent(SplashActivity.this, LoginActivity.class, Bundle.EMPTY, true,true);
            }
        }
    }
}
