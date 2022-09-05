package com.example.wakelock3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * This code only works if this app is currently open
 * when we send the braodcast from the other app
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.EndWakeLock);

        //We use PowerManager and WakeLock class to force the device awake
        //keep the screen awake on some event, such as phone call, we can throw
        //here we set a receiver to receive a broadcast, receive the IntentFilter in
        //in MainActivity2 the specific tag "com.mybroadcast.sendbroadcast", then use an
        //intent to open MainActivity, and instantiate the PowerManager and WakeLock

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "MyApp::MyWakelockTag");
        //PowerManager.WakeLock wakeLock2 = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "TAG");

        try{
            wakeLock.acquire();

        }catch(Exception e)
        {
            System.out.println("Wake lock did not succeed");
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);

        /**
         * This code sends a broadcast that will only accept the tag:
         * "com.mybroadcast.sendbroadcast"
         */
        //Intent intent = new Intent();
        //intent.setAction("com.mybroadcast.sendbroadcast");
        //intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        //sendBroadcast(intent);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //call the release() method to end the wakelock
                wakeLock.release();
            }
        });
    }
}