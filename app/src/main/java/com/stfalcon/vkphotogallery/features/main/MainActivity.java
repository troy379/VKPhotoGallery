package com.stfalcon.vkphotogallery.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.features.auth.AuthActivity;
import com.stfalcon.vkphotogallery.features.prefs.Preferences;
import com.stfalcon.vkphotogallery.features.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_AUTHORIZATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.with(this).getAccessToken() == null
                || Preferences.with(this).getUser() == 0) {
            AuthActivity.openForResult(this, REQUEST_CODE_AUTHORIZATION);
        } else {
            setStartFragment();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_AUTHORIZATION:
                if (resultCode != RESULT_OK) finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (getBackStackCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    private void setStartFragment() {
        if (getBackStackCount() == 0) {
            replaceFragment(ProfileFragment.newInstance());
        }
    }

    private int getBackStackCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }
}
