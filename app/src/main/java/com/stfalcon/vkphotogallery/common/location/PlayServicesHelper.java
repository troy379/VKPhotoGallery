package com.stfalcon.vkphotogallery.common.location;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/*
 * Created by troy379 on 18.01.17.
 */
final class PlayServicesHelper {
    private PlayServicesHelper() {
        throw new AssertionError();
    }

    public static boolean isAvailable(Context context) {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int code = api.isGooglePlayServicesAvailable(context);
        return code == ConnectionResult.SUCCESS;
    }
}
