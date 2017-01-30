package com.stfalcon.vkphotogallery.common.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

/*
 * Created by troy379 on 18.01.17.
 */
abstract class LocationApi
        implements ILocationApi {

    protected Context context;
    protected Location lastLocation;
    protected LocationChangedListener locationListener;

    public LocationApi(Context context) {
        this.context = context;
    }

    @Override
    public boolean isGPSExists() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    }

    @Override
    public Location getLastLocation() {
        return lastLocation;
    }

    @Override
    public void setLocationListener(LocationChangedListener locationListener) {
        this.locationListener = locationListener;
    }
}
