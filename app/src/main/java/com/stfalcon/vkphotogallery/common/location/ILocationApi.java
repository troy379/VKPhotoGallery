package com.stfalcon.vkphotogallery.common.location;

import android.location.Location;

/*
 * Created by troy379 on 18.01.17.
 */
public interface ILocationApi {

    void startLocationUpdates(Accuracy accuracy);

    void stopLocationUpdates();

    boolean isGPSTurnedOn();

    boolean isGPSExists();

    Location getLastLocation();

    void setLocationListener(LocationChangedListener locationListener);
}
