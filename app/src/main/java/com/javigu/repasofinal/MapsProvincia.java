package com.javigu.repasofinal;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javigu.repasofinal.databinding.ActivityMapsProvinciaBinding;

public class MapsProvincia extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsProvinciaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsProvinciaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        double p1,p2;
        String provincia;
        //obtener las coords del main activity
        Bundle b = getIntent().getExtras();
        if (b!=null){
            String coords = b.getString("maps_provincia");
            //separar las coordenadas
            String[] parts = coords.split(", ");
            String part1 = parts[0];
            String part2 = parts[1];
            if (part1.equalsIgnoreCase("39.472157") && part2.equalsIgnoreCase("-0.378292")){
                p1 =Double.parseDouble(part1);
                p2=Double.parseDouble(part2);
                provincia="vlc";
            }else if (part1.equalsIgnoreCase("38.377294") && part2.equalsIgnoreCase("-0.495102")){
                p1 =Double.parseDouble(part1);
                p2=Double.parseDouble(part2);
                provincia="alc";
            }else if (part1.equalsIgnoreCase("39.981539") && part2.equalsIgnoreCase("-0.048836")){
                p1 =Double.parseDouble(part1);
                p2=Double.parseDouble(part2);
                provincia="cst";
            }else if (part1.equalsIgnoreCase("40.405445") && part2.equalsIgnoreCase("-3.696158")){
                p1 =Double.parseDouble(part1);
                p2=Double.parseDouble(part2);
                provincia="mrd";
            }else{
                p1 =Double.parseDouble(part1);
                p2=Double.parseDouble(part2);
                provincia="bcn";
            }
            LatLng p = new LatLng(p1,p2);
            mMap.addMarker(new MarkerOptions().position(p).title(provincia));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(p));
        }
    }
}