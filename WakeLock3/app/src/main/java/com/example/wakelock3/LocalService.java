package com.example.wakelock3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


public class LocalService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter intentFilter = new IntentFilter("com.mybroadcast.sendbroadcast");
        MyBroadcastReceiver myReceiver = new MyBroadcastReceiver();
        registerReceiver(myReceiver, intentFilter);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter("com.mybroadcast.sendbroadcast");
        MyBroadcastReceiver myReceiver = new MyBroadcastReceiver();
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //preferably we want this to run and receive broadcast
        //even when the app is closed
        IntentFilter intentFilter = new IntentFilter("com.mybroadcast.sendbroadcast");
        MyBroadcastReceiver myReceiver = new MyBroadcastReceiver();
        registerReceiver(myReceiver, intentFilter);
        //We can unregister the receiver if we want to stop receiving broadcasts for any reason
        //unregisterReceiver(myReceiver);
    }

}