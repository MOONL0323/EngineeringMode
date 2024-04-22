package com.fibocom.engineeringMode.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fibocom.myapplication.R;

public class TouchScreenTestActivity extends AppCompatActivity {

    private int originalPointerLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_screen_test);
        // 保存原来的 POINTER_LOCATION 设置值
        originalPointerLocation = Settings.System.getInt(
                getContentResolver(), Settings.System.POINTER_LOCATION, 0);

        try {
            Settings.System.putInt(getContentResolver(), Settings.System.POINTER_LOCATION, 0);
        } catch (Exception e) {
            Log.d("error", "onCreate error");
            e.printStackTrace();
        }
        Button backButton = findViewById(R.id.button_back);
        backButton.setVisibility(View.VISIBLE); // 设置按钮为可见
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button startButton = findViewById(R.id.button_start);
        startButton.setVisibility(View.VISIBLE); // 设置按钮为可见

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开POINTER_LOCATION并且只在当前Activity中有效
                try {
                    Settings.System.putInt(getContentResolver(), Settings.System.POINTER_LOCATION, 1);
                } catch (Exception e) {
                    Log.d("error", "startButton onClick error");
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在页面暂停时恢复原来的 POINTER_LOCATION 设置值
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.POINTER_LOCATION, 0);
        } catch (Exception e) {
            Log.d("error", "onPause error");
            e.printStackTrace();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        // 在页面停止时将 POINTER_LOCATION 设置为0
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.POINTER_LOCATION, 0);
        } catch (Exception e) {
            Log.d("error", "onStop error");
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 在页面销毁时恢复原来的 POINTER_LOCATION 设置值
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.POINTER_LOCATION, 0);
        } catch (Exception e) {
            Log.d("error", "onDestroy error");
            e.printStackTrace();
        }
    }

}