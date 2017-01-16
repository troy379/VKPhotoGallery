package com.stfalcon.vkphotogallery.common.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.stfalcon.vkphotogallery.common.events.AccessTokenHasExpiredEvent;
import com.stfalcon.vkphotogallery.features.auth.AuthActivity;
import com.stfalcon.vkphotogallery.features.prefs.Preferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/*
 * Created by troy379 on 16.01.17.
 */
public class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_AUTHORIZATION = 0;

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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onAccessTokenHasExpired(AccessTokenHasExpiredEvent event) {
        Preferences.with(this).clearAuthData();
        openAuthActivity();
    }

    protected boolean isAuthorized() {
        if (Preferences.with(this).getAccessToken() == null
                || Preferences.with(this).getUser() == 0) {
            openAuthActivity();
            return false;
        } else return true;
    }

    private void openAuthActivity() {
        AuthActivity.openForResult(this, REQUEST_CODE_AUTHORIZATION);
    }
}
