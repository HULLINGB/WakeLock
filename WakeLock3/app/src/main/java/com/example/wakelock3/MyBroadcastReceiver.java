package com.example.wakelock3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("BroadcastReceiver", "This is our message");
        Toast.makeText(context, "This is our message", Toast.LENGTH_LONG);


    }
}