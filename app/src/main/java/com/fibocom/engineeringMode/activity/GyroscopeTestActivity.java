package com.fibocom.engineeringMode.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.utils.Util;
import com.fibocom.myapplication.R;

public class GyroscopeTestActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private TextView gyroscopeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_test);

        gyroscopeTextView = findViewById(R.id.gyroscope_text_view);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //按返回键返回上一个页面
        Button button1 = findViewById(R.id.button_back);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });

        DBopenhelper dBopenhelper = DBopenhelper.getInstance(this, "test.db", null, 1);
        Util.alertDialog("陀螺仪测试","陀螺仪测试是否正常",this,"gyroscope",dBopenhelper,0,null);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeTextView.setText("Gyroscope Data: \n" +
                    "X: " + event.values[0] + "\n" +
                    "Y: " + event.values[1] + "\n" +
                    "Z: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}