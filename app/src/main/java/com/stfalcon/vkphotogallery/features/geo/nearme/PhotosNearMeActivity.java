package com.stfalcon.vkphotogallery.features.geo.nearme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stfalcon.vkphotogallery.R;
import com.stfalcon.vkphotogallery.features.places.autocomplete.PlacesAutocompleteActivity;

/*
 * Created by troy379 on 06.02.17.
 */
public class PhotosNearMeActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private static final int REQUEST_CODE_PLACES = 0;

    private GoogleMap map;

    private double longitude, latitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_near_me);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map))
                .getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.near_me_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (map != null) {
            switch (item.getItemId()) {
                case R.id.action_search_by_location:
                    // TODO: 06.02.17 request location
                    break;
                case R.id.action_search_by_place:
                    startActivityForResult(new Intent(this, PlacesAutocompleteActivity.class), REQUEST_CODE_PLACES);
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_PLACES:
                    Bundle extras = data.getExtras();
                    this.longitude = extras.getDouble(PlacesAutocompleteActivity.KEY_LONGITUDE, 0.0f);
                    this.latitude = extras.getDouble(PlacesAutocompleteActivity.KEY_LATITUDE, 0.0f);
                    break;
            }
            // TODO: 06.02.17 request
            LatLng position = new LatLng(latitude, longitude);

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17));
            map.addMarker(new MarkerOptions().position(position).title("Selected place"));

            Log.d("WTFWTF", "onActivityResult: lat: " + latitude + ", lon: " +  longitude);
        }
    }
}
