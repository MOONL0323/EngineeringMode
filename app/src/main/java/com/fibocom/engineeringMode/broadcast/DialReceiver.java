package com.fibocom.engineeringMode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fibocom.engineeringMode.activity.ManualEngineeringModeActivity;

public class DialReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("监听打电话", "onReceive");
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }

        if (phoneNumber.equals("*#8001#*")) {
            setResultData(null); // End the call

            // Start your app
            Intent appIntent = new Intent(context, ManualEngineeringModeActivity.class);
            appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(appIntent);
        }
    }
}