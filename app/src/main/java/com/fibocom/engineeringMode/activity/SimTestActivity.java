package com.fibocom.engineeringMode.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.fibocom.engineeringMode.db.DBopenhelper;
import com.fibocom.engineeringMode.utils.Util;
import com.fibocom.myapplication.R;

public class SimTestActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_test);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            displaySimState();
        }

        //按返回键返回上一个页面
        Button button1 = findViewById(R.id.button_back);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });

        DBopenhelper dBopenhelper = DBopenhelper.getInstance(this, "test.db", null, 1);
        Util.alertDialog("SIM卡测试","SIM卡是否正常",this,"sim",dBopenhelper,0,null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displaySimState();
        }
    }

    private void displaySimState() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String simState = "SIM State: " + (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY ? "Ready" : "Not Ready");

        TextView textView = findViewById(R.id.textView);
        textView.setText(simState);
    }
}