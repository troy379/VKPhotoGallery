package com.stfalcon.vkphotogallery.common.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/*
 * Created by troy379 on 18.01.17.
 */
@SuppressWarnings("MissingPermission")
class PlayServicesLocationApi extends LocationApi
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static String TAG = LocationManagerApi.class.getCanonicalName();
    private static final int RETRY_TIMEOUT = 5000; // 5 secs

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    public PlayServicesLocationApi(Context context) {
        super(context);

        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void startLocationUpdates(final Accuracy accuracy) {
        switch (accuracy) {
            case LOW:
                locationRequest = new LocationRequest();
                locationRequest.setInterval(30 * 1000); // 30 secs
                locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
                break;
            case HIGH:
                locationRequest = new LocationRequest();
                locationRequest.setInterval(2000);  // 2 sec
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                break;
        }

        try {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(googleApiClient, locationRequest, this);
        } catch (IllegalStateException e) {
            googleApiClient.connect();
            doWithDelay(new Runnable() {
                @Override
                public void run() {
                    startLocationUpdates(accuracy);
                }
            }, RETRY_TIMEOUT);
        }
    }

    @Override
    public void stopLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        } catch (IllegalStateException e) {
            doWithDelay(new Runnable() {
                @Override
                public void run() {
                    stopLocationUpdates();
                }
            }, RETRY_TIMEOUT);  // Try after 10sec
        }
    }

    @Override
    public boolean isGPSTurnedOn() {
        LocationAvailability availability = LocationServices
                .FusedLocationApi.getLocationAvailability(googleApiClient);
        return availability.isLocationAvailable();
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (locationListener != null) {
            locationListener.onLocationChanged(location);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    private void doWithDelay(Runnable runnable, int delay) {
        new Handler().postDelayed(runnable, delay);
    }
}
