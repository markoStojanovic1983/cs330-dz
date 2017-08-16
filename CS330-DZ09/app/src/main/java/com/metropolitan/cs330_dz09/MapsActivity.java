package com.metropolitan.cs330_dz09;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by mare on 6/11/17.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        m = new LatLng(data.getFloat("lat"),
                data.getFloat("long"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(m).title("Moj marker"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m, 15));


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            }
        });

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(),
                    "Dozvolite pristup lokaciji",
                    Toast.LENGTH_LONG);
        }

        final Geocoder gk = new Geocoder(getBaseContext(), Locale.getDefault());

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                try {
                    List<android.location.Address> adr = gk.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Moj marker"));
                    String output = "";
                    if (adr.size() > 0) {
                        for (int i = 0; i <= adr.get(0).getMaxAddressLineIndex(); i++) {
                            output += adr.get(0).getAddressLine(i) + "\n";
                        }
                        Toast.makeText(getBaseContext(), output, Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            List<android.location.Address> adr = gk.getFromLocation(m.latitude, m.longitude, 1);

            String output = "";
            if (adr.size() > 0) {
                for (int i = 0; i < adr.get(0).getMaxAddressLineIndex(); i++) {
                    output += adr.get(0).getAddressLine(i) + "\n";
                }
                Toast.makeText(getBaseContext(), output, Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
