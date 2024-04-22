package com.fibocom.engineeringMode.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.bean.GlobalContentValues;
import com.fibocom.engineeringMode.db.DbUtil;
import com.fibocom.engineeringMode.db.dbimpl.DbUtilImpl;
import com.fibocom.myapplication.R;

public class ManualEngineeringModeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    private MediaProjectionManager mProjectionManager;
    private MediaProjection mMediaProjection;
    private static DbUtil dbUtil = new DbUtilImpl();

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MainProjectTestActivity", "onCreate");

        setContentView(R.layout.home_project);

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
                Intent intent = new Intent(ManualEngineeringModeActivity.this, TouchScreenTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonScreen = findViewById(R.id.button_screen);
        buttonScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, ScreenTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCamera = findViewById(R.id.button_camera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, CameraTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSim = findViewById(R.id.button_sim);
        buttonSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, SimTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTemperature = findViewById(R.id.button_temperature);
        buttonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, TemperatureTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonGyroscope = findViewById(R.id.button_gyroscope);
        buttonGyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, GyroscopeTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonGnss = findViewById(R.id.button_gnss);
        buttonGnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, GnssTestActivity.class);
                startActivity(intent);
            }
        });

        Button buttonVersion = findViewById(R.id.button_version_info);
        buttonVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualEngineeringModeActivity.this, VersionInfoActivity.class);
                startActivity(intent);
            }
        });
        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBopenhelper dBopenhelper = DBopenhelper.getInstance(ManualEngineeringModeActivity.this, "test.db", null, 1);
                ContentValues values = GlobalContentValues.getInstance(0);
                Log.d("MainProjectTestActivity", "onClick: " + values);
                dbUtil.saveCommit(dBopenhelper, values, 0);
            }
        });

    }
}


