package com.fibocom.engineeringMode.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.utils.Util;
import com.fibocom.myapplication.R;

public class TemperatureTestActivity extends AppCompatActivity {

    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_test);

        temperatureTextView = findViewById(R.id.temperature_text_view);

        this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        Button button1 = findViewById(R.id.button_back);
        button1.setOnClickListener(v -> finish());

        DBopenhelper dBopenhelper = DBopenhelper.getInstance(this, "test.db", null, 1);
        Util.alertDialog("温度测试","温度是否正常",this,"temperature",dBopenhelper,0,null);
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            temperatureTextView.setText("Temperature: " + (float)temperature / 10 + "°C");
        }
    };
}