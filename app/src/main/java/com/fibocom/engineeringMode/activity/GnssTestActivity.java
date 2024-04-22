package com.fibocom.engineeringMode.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.utils.Util;
import com.fibocom.myapplication.R;

import java.util.Iterator;

public class GnssTestActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private TextView gpsUpdateTime, gpsSatelliteNumer, gpsLatitude, gpsLongitude, gpsSpeed, gpsAltitude, gpsAccuracy, gpsBearing;
    private TextView gpsSattelites;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnss_test);


        /*
          以下为GPS信息显示
          1. gpsUpdateTime: GPS信息更新时间
          2. gpsSatelliteNumer: GPS卫星数量
          3. gpsLatitude: 纬度
          4. gpsLongitude: 经度
          5. gpsSpeed: 速度
          6. gpsAltitude: 海拔
          7. gpsAccuracy: 精度
          8. gpsBearing: 方向
         */

        gpsUpdateTime = findViewById(R.id.gps_timestamp);
        gpsSatelliteNumer = findViewById(R.id.gps_satellite_number);
        gpsLatitude = findViewById(R.id.gps_latitude);
        gpsLongitude = findViewById(R.id.gps_longitude);
        gpsSpeed = findViewById(R.id.gps_speed);
        gpsAltitude = findViewById(R.id.gps_altitude);
        gpsAccuracy = findViewById(R.id.gps_accuracy);
        gpsBearing = findViewById(R.id.gps_bearing);

        gpsSattelites = findViewById(R.id.gps_satellites);

        //各种按钮
        Button buttonStart = findViewById(R.id.start_gnss_test);
        Button buttonBack = findViewById(R.id.back_gnss_test);

        //触发按钮
        buttonStart.setOnClickListener(v -> {
            Log.d("GnssTestActivity", "Start GNSS test");
            //开始进行GPS信息的获取
            //获取位置管理器
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


            //判断GPS是否打开
            if (!isGpsAble(locationManager)) {
                Log.d("GnssTestActivity", "GPS is not enabled");
                openGPS2();
            }


            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            updateShow(location);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,8,new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    updateShow(location);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.d("GnssTestActivity", "onStatusChanged");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    updateShow(locationManager.getLastKnownLocation(provider));
                    Log.d("GnssTestActivity", "onProviderEnabled");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    updateShow(null);
                    Log.d("GnssTestActivity", "onProviderDisabled");
                }

            });
            final GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
                public void onGpsStatusChanged(int event) {
                    if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
                        if (ActivityCompat.checkSelfPermission(GnssTestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            GpsStatus gpsStatus = locationManager.getGpsStatus(null);
                            Iterable<GpsSatellite> satellites = gpsStatus.getSatellites();
                            Iterator<GpsSatellite> sat = satellites.iterator();
                            int count = 0;
                            while (sat.hasNext()) {
                                GpsSatellite satellite = sat.next();
                                if (satellite.getSnr() > 30 && satellite.usedInFix()) {
                                    count++;
                                }
                            }
                            // 更新UI显示真星个数
                            gpsSattelites.setText(String.valueOf(count));
                        }
                    }
                }
            };
            DBopenhelper dBopenhelper = DBopenhelper.getInstance(this, "test.db", null, 1);
            Util.alertDialog("GPS测试结果", "GPS测试是否正常", this, "gnss", dBopenhelper,0,null);

        });

        buttonBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void updateShow(Location location) {
        if (location != null) {
            gpsUpdateTime.setText(String.valueOf(System.currentTimeMillis()));
            gpsLatitude.setText(String.valueOf(location.getLatitude()));
            gpsLongitude.setText(String.valueOf(location.getLongitude()));
            gpsSpeed.setText(String.valueOf(location.getSpeed()));
            gpsAltitude.setText(String.valueOf(location.getAltitude()));
            gpsAccuracy.setText(String.valueOf(location.getAccuracy()));
            gpsBearing.setText(String.valueOf(location.getBearing()));
        }else{
            Log.d("GnssTestActivity", "location is null");
            gpsUpdateTime.setText("0");
            gpsLatitude.setText("0");
            gpsLongitude.setText("0");
            gpsSpeed.setText("0");
            gpsAltitude.setText("0");
            gpsAccuracy.setText("0");
            gpsBearing.setText("0");
        }
    }


    private boolean isGpsAble(LocationManager lm){
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    //打开位置信息设置页面让用户自己设置
    private void openGPS2(){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent,0);
    }

}