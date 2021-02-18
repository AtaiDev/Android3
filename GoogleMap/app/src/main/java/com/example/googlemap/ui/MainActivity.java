package com.example.googlemap.ui;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.googlemap.R;
import com.example.googlemap.data.AppDatabase;
import com.example.googlemap.data.local.models.LatLngCord;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private final static int LOCATION_REQUEST_CODE = 9;
    private static final String KEY_LATLNG = "latlng";
    private com.example.googlemap.databinding.ActivityMainBinding binding;
    private GoogleMap map;
    private final List<LatLng> latLngList = new ArrayList<>();

    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.googlemap.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appDatabase = AppDatabase.getInstance(this);
        setMapFragment();
        btnListeners();

        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_REQUEST_CODE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (ContextCompat
                    .checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {

                map.setMyLocationEnabled(true);

            }
        }
    }

    private void btnListeners() {

        if (!appDatabase.coordinateDao().getAll().isEmpty()) {
            for (int i = 0; i < appDatabase.coordinateDao().getAll().size(); i++) {
                latLngList.add(appDatabase.coordinateDao().getAll().get(i).getLatLng());
            }
        }

        binding.applyGone.setOnClickListener(v -> {
//            PolygonOptions polygonOptions = new PolygonOptions().addAll(latLngList)
//                    .strokeColor(R.color.yellow)
//                    .strokeWidth(5f);

            if (!latLngList.isEmpty()) {
                map.addPolygon(
                        new PolygonOptions()
                                .addAll(latLngList)
                                .strokeColor(Color.RED)
                                .fillColor(Color.BLACK)
                                .strokeWidth(4f));
            } else {
                Toast.makeText(this, "Please provide markers", Toast.LENGTH_SHORT).show();
            }
        });

        binding.applyLine.setOnClickListener(v -> {
            map.addPolyline(
                    new PolylineOptions()
                            .addAll(latLngList)
                            .endCap(new RoundCap())
            );
        });

    }

    private void setMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_google);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        CameraPosition position = CameraPosition.fromLatLngZoom(new LatLng(42.8777087,74.6225106),15);
        map = googleMap;

        if (!appDatabase.coordinateDao().getAll().isEmpty()) {
            for (int i = 0; i < appDatabase.coordinateDao().getAll().size(); i++) {
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(appDatabase
                                .coordinateDao()
                                .getAll().get(i)
                                .getLatLng())
                        .draggable(true)
                        .anchor(0.68f, 0.35f)
                        .icon(vectorToBitmap(R.drawable.ic_cursor, Color.parseColor("#f50049")));
                map.addMarker(markerOptions);
            }
        }


        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.8777087, 74.6225106), 15));
        map.getUiSettings().setZoomControlsEnabled(true);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

            map.setMyLocationEnabled(true);

        }


    }

    @Override
    public void onMapClick(LatLng latLng) {


        appDatabase.coordinateDao().insertLatLng(new LatLngCord(latLng));

        Log.e(KEY_LATLNG, "onMapClick: " + latLng.latitude + " " + latLng.longitude);

        latLngList.add(latLng);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .draggable(true)
                .anchor(0.68f, 0.35f)
                .icon(vectorToBitmap(R.drawable.ic_cursor, Color.parseColor("#f50049")));


        map.addMarker(markerOptions);

        Log.e(KEY_LATLNG, "onMapClick: " + appDatabase.coordinateDao().getAll().toString());

    }

    // svg to Drawable without color custom;
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        assert vectorDrawable != null;
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    // with custom color to icon !
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        assert vectorDrawable != null;
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.remove();
        appDatabase.coordinateDao().deleteLatLng(marker.getPosition());
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal_map:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.hybrid_map:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.terrain_map:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.satellite_map:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.clear_map:
                map.clear();
                appDatabase.coordinateDao().deleteAll();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}