package com.example.myspot;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity; 
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class mapActivity extends Activity{
    // Google Map
    private GoogleMap googleMap;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        Button myLocate = (Button)findViewById(R.id.mybt);
        try {
            // Loading map
            startMyMap();
  
            myLocate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                	// latitude and longitude
                	double latitude = 0.0;
                	double longitude = 0.0;
                	LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                	Criteria criteria = new Criteria();
                	String provider = service.getBestProvider(criteria, false);
                	Location location = service.getLastKnownLocation(provider);
                	LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());               	

                	latitude=userLocation.latitude;
                    longitude=userLocation.longitude;
                	
                	// create marker
                	MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");
                	 
                	// adding marker
                	googleMap.addMarker(marker);
                	
                	// move and zoom  to location
                	
                	googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        			Toast.makeText(getApplicationContext(), "This is mySpot", Toast.LENGTH_LONG).show();
                }
            });
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void startMyMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
//            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
  //          googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "No Map Available", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMyMap();
    }
}
