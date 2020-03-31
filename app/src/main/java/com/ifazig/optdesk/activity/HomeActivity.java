package com.ifazig.optdesk.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ifazig.optdesk.R;
import com.ifazig.optdesk.adapter.NavigationMenuAdapters;
import com.ifazig.optdesk.api_model.NavigationMenuModel;
import com.ifazig.optdesk.databinding.ActivityHomeBinding;
import com.ifazig.optdesk.enums.NavigationMenuEnum;
import com.ifazig.optdesk.fragment.HomeFragment;
import com.ifazig.optdesk.interfaces.NavigationClick;
import com.ifazig.optdesk.utils.CommonFunctions;
import com.ifazig.optdesk.utils.LanguageConstants;
import com.ifazig.optdesk.utils.SessionManager;
import com.ifazig.optdesk.utils.SharedPrefConstants;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    private FragmentManager fragmentManager;
    private boolean doubleBackToExitPressedOnce = false;

    private TextView tvUserName, tvUserMobile;
    private ImageView iv_profilepicture;

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        iv_profilepicture = (ImageView) findViewById(R.id.iv_profilepicture);
        /*if (SessionManager.getInstance().getFromPreference(SharedPrefConstants.PROFILEPIC) != null && !SessionManager.getInstance().getFromPreference(SharedPrefConstants.PROFILEPIC).isEmpty()) {
            Glide.with(HomeActivity.this).load(SessionManager.getInstance().getFromPreference(SharedPrefConstants.PROFILEPIC)).apply(RequestOptions.circleCropTransform()).into(iv_profilepicture);
        }*/
        tvUserName.setText(SessionManager.getInstance().getFromPreference(SharedPrefConstants.FIRST_NAME));
        fragmentManager.beginTransaction()
                .replace(R.id.flContainer, new HomeFragment(), "home")
                .commit();
        loadNavigationMenu();
    }

    // ----- Navigation
    private void loadNavigationMenu() {
        List<NavigationMenuModel> navigationMenuModels = new ArrayList<>();

        NavigationMenuModel navigationMenuModel;
        // --- my profile
        navigationMenuModel = new NavigationMenuModel();
        navigationMenuModel.setNavigationItemId(NavigationMenuEnum.MY_PROFILE);
        navigationMenuModel.setNavigationIcon(R.drawable.ic_myprofile);
        navigationMenuModel.setNavigationItemName(LanguageConstants.myprofile);
        navigationMenuModels.add(navigationMenuModel);
// --- my bookings
        navigationMenuModel = new NavigationMenuModel();
        navigationMenuModel.setNavigationItemId(NavigationMenuEnum.MY_BOOKINGS);
        navigationMenuModel.setNavigationIcon(R.drawable.ic_mybookings);
        navigationMenuModel.setNavigationItemName(LanguageConstants.mybookings);
        navigationMenuModels.add(navigationMenuModel);
// --- help&support
        navigationMenuModel = new NavigationMenuModel();
        navigationMenuModel.setNavigationItemId(NavigationMenuEnum.HELP_SUPPORT);
        navigationMenuModel.setNavigationIcon(R.drawable.ic_help_support);
        navigationMenuModel.setNavigationItemName(LanguageConstants.helpsupport);
        navigationMenuModels.add(navigationMenuModel);
// --- logout
        navigationMenuModel = new NavigationMenuModel();
        navigationMenuModel.setNavigationItemId(NavigationMenuEnum.LOGOUT);
        navigationMenuModel.setNavigationIcon(R.drawable.ic_logout_new);
        navigationMenuModel.setNavigationItemName(LanguageConstants.logout);
        navigationMenuModels.add(navigationMenuModel);
        NavigationMenuAdapters navigationMenuAdapter = new NavigationMenuAdapters(HomeActivity.this, navigationMenuModels, new NavigationClick() {
            @Override
            public void onClick(NavigationMenuModel navigationMenuModel) {
                navigationMenuClick(navigationMenuModel);
            }
        });
        binding.lvNavigation.setAdapter(navigationMenuAdapter);
    }


    // ---- Navigation Menu Click
    private void navigationMenuClick(NavigationMenuModel navigationMenuModel) {
        if (navigationMenuModel.getNavigationItemId() == NavigationMenuEnum.MY_PROFILE) {
            //CommonFunctions.getInstance().newIntent(HomeActivity.this, MyProfileActivity.class, Bundle.EMPTY, false, false);

        } else if (navigationMenuModel.getNavigationItemId() == NavigationMenuEnum.MY_BOOKINGS) {
            CommonFunctions.getInstance().newIntent(HomeActivity.this, MyBookingsActivity.class, Bundle.EMPTY, false, false);

        } else if (navigationMenuModel.getNavigationItemId() == NavigationMenuEnum.HELP_SUPPORT) {
            CommonFunctions.getInstance().newIntent(HomeActivity.this, MyProfileActivity.class, Bundle.EMPTY, false, false);
        } else if (navigationMenuModel.getNavigationItemId() == NavigationMenuEnum.LOGOUT) {
            logOutDialog();
        }
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    // ----- On BackPressed
    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        // Toast.makeText(this, "count:"+count, Toast.LENGTH_SHORT).show();
        if (count == 1) {
            super.onBackPressed();
        }

        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            CommonFunctions.getInstance().validationEmptyError(HomeActivity.this, LanguageConstants.pleaseClickBackAgainToExit);
        }
    }

    // ---- LogOut Dialog
    private void logOutDialog() {
        final Dialog dialogLogOut = new Dialog(HomeActivity.this);
        dialogLogOut.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLogOut.setContentView(R.layout.dialog_logout);
        dialogLogOut.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogLogOut.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialogLogOut.setCancelable(false);

        TextView txtMsg = (TextView) dialogLogOut.findViewById(R.id.txtMsg);
        TextView txtClose = (TextView) dialogLogOut.findViewById(R.id.txtClose);
        TextView txtOk = (TextView) dialogLogOut.findViewById(R.id.txtOk);

        txtMsg.setText(LanguageConstants.areYouSureWantToLogout);
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
                SessionManager.getInstance().Logout();
                CommonFunctions.getInstance().newIntent(HomeActivity.this, LoginActivity.class, Bundle.EMPTY, true, true);
            }
        });
    }


    // -- On Activity Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
