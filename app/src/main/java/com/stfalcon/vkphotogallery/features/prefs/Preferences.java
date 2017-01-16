package com.stfalcon.vkphotogallery.features.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/*
 * Created by troy379 on 28.12.16.
 */
public class Preferences {

    private static final String APP_KEY = "vk_photo_gallery_";

    private static final String PREFERENCES_NAME = APP_KEY + "shared_preferences_";
    private static final String KEY_ACCESS_TOKEN = PREFERENCES_NAME + "access_token";
    private static final String KEY_USER_ID = "user_id";

    private Context context;

    private static Preferences instance;

    public static Preferences with(Context context) {
        if (instance == null) instance = new Preferences(context);
        return instance;
    }

    private Preferences(Context context) {
        this.context = context;
    }

    public SharedPreferences getReader() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
    }

    /*
    * USER AUTH
    * */
    public String getAccessToken() {
        return getReader().getString(KEY_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String token) {
        getEditor().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public void setUser(long userId) {
        getEditor().putLong(KEY_USER_ID, userId).apply();
    }

    public long getUser() {
        return getReader().getLong(KEY_USER_ID, 0);
    }

    public void clearAuthData() {
        setAccessToken(null);
        setUser(0);
    }
}