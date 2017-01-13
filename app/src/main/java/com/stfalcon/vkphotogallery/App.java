package com.stfalcon.vkphotogallery;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

/*
 * Created by troy379 on 16.12.16.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Fresco.initialize(this);
        Realm.init(this);
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
