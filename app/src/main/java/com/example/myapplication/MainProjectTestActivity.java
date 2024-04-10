package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainProjectTestActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    private MediaProjectionManager mProjectionManager;
    private MediaProjection mMediaProjection;

    private static final int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS = 1;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MainProjectTestActivity", "onCreate");

        setContentView(R.layout.home_project);

        //请求权限
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.PROCESS_OUTGOING_CALLS}, MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS);
        }
        //按返回键返回上一个页面
        Button button1 = findViewById(R.id.button_back);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonTouchScreen = findViewById(R.id.button_touch_screen);
        buttonTouchScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, TouchScreenTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonScreen = findViewById(R.id.button_screen);
        buttonScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, ScreenTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCamera = findViewById(R.id.button_camera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, CameraTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSim = findViewById(R.id.button_sim);
        buttonSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, SimTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTemperature = findViewById(R.id.button_temperature);
        buttonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, TemperatureTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonGyroscope = findViewById(R.id.button_gyroscope);
        buttonGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, GyroscopeTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonGnss = findViewById(R.id.button_gnss);
        buttonGnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, GnssTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonBattery = findViewById(R.id.button_version_info);
        buttonBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProjectTestActivity.this, VersionInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}


