package com.stfalcon.vkphotogallery.features.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.features.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getBackStackCount() == 0) {
            replaceFragment(ProfileFragment.newInstance());
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getBackStackCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    private int getBackStackCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }
}
