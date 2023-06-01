package com.example.a71p;

<<<<<<< HEAD
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

>>>>>>> 91p/master
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
<<<<<<< HEAD
import java.util.ArrayList;
=======

>>>>>>> 91p/master
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private List<Advert> advertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

<<<<<<< HEAD
        advertList = getIntent().getParcelableArrayListExtra("advertList");
    }


=======
        advertList = getAdverts();
    }

>>>>>>> 91p/master
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        showAdvertsOnMap();
    }

<<<<<<< HEAD
    private void showAdvertsOnMap() {
        if (googleMap != null && advertList != null && !advertList.isEmpty()) {
=======
    private List<Advert> getAdverts() {
        AdvertDatabaseHelper databaseHelper = new AdvertDatabaseHelper(this);
        return databaseHelper.getAllAdverts();
    }

    private void showAdvertsOnMap() {
        if (googleMap != null && !advertList.isEmpty()) {
>>>>>>> 91p/master
            for (Advert advert : advertList) {
                LatLng location = new LatLng(advert.getLatitude(), advert.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(location)
                        .title(advert.getName());
                googleMap.addMarker(markerOptions);
            }

            LatLng firstLocation = new LatLng(advertList.get(0).getLatitude(), advertList.get(0).getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12));
        }
    }
}
