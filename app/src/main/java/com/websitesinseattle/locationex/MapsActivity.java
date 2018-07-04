package com.websitesinseattle.locationex;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Meeting> meetingsArray = new ArrayList<>();
        Meeting meeting1 = new Meeting();
        meeting1.setName("Seattle Central College");
        meeting1.setAddress("4006 24th Pl S");

        Meeting meeting2 = new Meeting();
        meeting2.setName("Meeting 2");
        meeting2.setAddress("9076 Beacon Ave S");

        Meeting meeting3 = new Meeting();
        meeting3.setName("Meeting 3");
        meeting3.setAddress("321 Broadway Ave E");

        // for (dbobject : database ){
                //create a Meeting object
                //Meeting meeting = new Meeting(name,location,latitude,longitude);
                //add Meeting object to the meetingsArray
                //meetingsArray.add(meeting);
        //}

        Meeting meeting4 = new Meeting("Meeting 4", "420 Broadway Ave E, Seattle WA");

        meetingsArray.add(meeting1);
        meetingsArray.add(meeting2);
        meetingsArray.add(meeting3);
        meetingsArray.add(meeting4);



        for (Meeting meeting : meetingsArray ){
            // Add a marker in Sydney and move the camera
            LatLng marker = new LatLng(getLats(meeting.getAddress()), getLongs(meeting.getAddress()));
            mMap.addMarker(new MarkerOptions().position(marker).title(meeting.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                mMap.setMyLocationEnabled(true);
//                mMap.setOnMyLocationButtonClickListener(this);
//                mMap.setOnMyLocationClickListener(this);
            } else {
                // Show rationale and request permission.
            }
        }


    }

    //method to retrieve latitude from a given String Address
    public double getLats(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        double lats = 0;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
            }
            Address location=address.get(0);
            location.getLatitude();

            lats = location.getLatitude();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lats;
    }

    //method to retrieve longitude from a given String Address
    public double getLongs(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        double longs = 0;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
            }
            Address location=address.get(0);
            location.getLongitude();

            longs = location.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return longs;
    }
}
