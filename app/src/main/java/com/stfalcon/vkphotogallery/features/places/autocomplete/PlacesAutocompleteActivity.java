package com.stfalcon.vkphotogallery.features.places.autocomplete;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.stfalcon.vkphotogallery.R;

public class PlacesAutocompleteActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        PlacesRecyclerAdapter.OnPlaceSelectedListener {

    public static final String KEY_NAME = "NAME";
    public static final String KEY_ADDRESS = "ADDRESS";
    public static final String KEY_LONGITUDE = "LONGITUDE";
    public static final String KEY_LATITUDE = "LATITUDE";

    private EditText tvInput;
    private RecyclerView recyclerView;

    private GoogleApiClient googleApiClient;
    private PlacesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_autocomplete);

        tvInput = (EditText) findViewById(R.id.tvInput);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    public void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onPlaceSelected(String placeId) {
        Places.GeoDataApi
                .getPlaceById(googleApiClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        Place place = places.get(0);

                        Intent intent = new Intent();
                        intent.putExtra(KEY_NAME, place.getName());
                        intent.putExtra(KEY_ADDRESS, place.getAddress());
                        intent.putExtra(KEY_LONGITUDE, place.getLatLng().longitude);
                        intent.putExtra(KEY_LATITUDE, place.getLatLng().latitude);
                        setResult(RESULT_OK, intent);

                        places.release();
                        finish();
                    }
                });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        init();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PlacesRecyclerAdapter(this, googleApiClient, null);
        adapter.setPlaceSelectedListener(this);
        recyclerView.setAdapter(adapter);

        tvInput.addTextChangedListener(new QueryListener() {
            @Override
            public void onQueryChanged(String query) {
                adapter.getFilter().filter(query);
            }
        });
    }
}
