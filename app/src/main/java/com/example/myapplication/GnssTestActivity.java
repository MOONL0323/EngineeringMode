package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Iterator;

public class GnssTestActivity extends AppCompatActivity implements LocationListener, GpsStatus.Listener {
    private LocationManager locationManager;
    private TextView gpsUpdateTime, gpsSatellites, gpsLatitude, gpsLongitude, gpsSpeed, gpsAltitude, gpsAccuracy, gpsBearing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnss_test);

        gpsUpdateTime = findViewById(R.id.gps_update_time);
        gpsSatellites = findViewById(R.id.gps_satellites);
        gpsLatitude = findViewById(R.id.gps_latitude);
        gpsLongitude = findViewById(R.id.gps_longitude);
        gpsSpeed = findViewById(R.id.gps_speed);
        gpsAltitude = findViewById(R.id.gps_altitude);
        gpsAccuracy = findViewById(R.id.gps_accuracy);
        gpsBearing = findViewById(R.id.gps_bearing);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.addGpsStatusListener(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        gpsUpdateTime.setText(String.valueOf(System.currentTimeMillis()));
        gpsLatitude.setText(String.valueOf(location.getLatitude()));
        gpsLongitude.setText(String.valueOf(location.getLongitude()));
        gpsSpeed.setText(String.valueOf(location.getSpeed()));
        gpsAltitude.setText(String.valueOf(location.getAltitude()));
        gpsAccuracy.setText(String.valueOf(location.getAccuracy()));
        gpsBearing.setText(String.valueOf(location.getBearing()));


    }

    @Override
    public void onGpsStatusChanged(int event) {
        if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                GpsStatus gpsStatus = locationManager.getGpsStatus(null);
                int count = 0;
                Iterable<GpsSatellite> satellites = gpsStatus.getSatellites();
                for (GpsSatellite satellite : satellites) {
                    count++;
                }
                gpsSatellites.setText(String.valueOf(count));
            }
        }
    }

    // Other methods from LocationListener and GpsStatus.Listener
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}