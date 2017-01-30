package com.stfalcon.vkphotogallery.common.location;

import android.content.Context;
import android.location.Location;

/*
 * Created by troy379 on 18.01.17.
 */
public class LocationHelper implements ILocationApi {

    private ILocationApi api;

    public LocationHelper(Context context) {
        if (PlayServicesHelper.isAvailable(context)) {
            api = new PlayServicesLocationApi(context);
        } else api = new LocationManagerApi(context);
    }

    @Override
    public void startLocationUpdates(Accuracy accuracy) {
        api.startLocationUpdates(accuracy);
    }

    @Override
    public void stopLocationUpdates() {
        api.stopLocationUpdates();
    }

    @Override
    public boolean isGPSTurnedOn() {
        return api.isGPSTurnedOn();
    }

    @Override
    public boolean isGPSExists() {
        return api.isGPSExists();
    }

    @Override
    public Location getLastLocation() {
        return api.getLastLocation();
    }

    @Override
    public void setLocationListener(LocationChangedListener locationListener) {
        api.setLocationListener(locationListener);
    }
}
