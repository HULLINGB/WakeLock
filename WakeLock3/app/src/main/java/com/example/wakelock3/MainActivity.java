package com.example.wakelock3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * This code locks the screen and prevents the keyboard from
 * appearing, and uses a foreground service to register a
 * receiver to receive broadcasts
 */
public class MainActivity extends AppCompatActivity {

    Intent intent = new Intent(getApplicationContext(), LocalService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.EndWakeLock);

        //Start service. Using a foreground service will keep stop the system from stopping the service
        startForegroundService(intent);
        //startService(intent);

        //We use PowerManager and WakeLock class to force the device awake
        //keep the screen awake on some event, such as phone call, we can throw
        //here we set a receiver to receive a broadcast, receive the IntentFilter in
        //in MainActivity2 the specific tag "com.mybroadcast.sendbroadcast"

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
        //sendBroadcast();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //call the release() method to end the wakelock
                wakeLock.release();
                //stop service and unregister the receiver if we want to
                Intent intent2 = new Intent(getApplicationContext(), LocalService.class);
                //stopService(intent2);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.release();
        //stop service and unregister the receiver if we want to
        //stopService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        startService(intent);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.release();
        //stop service if we want
        //stopService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        startService(intent);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.release();
        //stop service if we want
        //stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "MyApp::MyWakelockTag");
        wakeLock.release();
        //stop service if we want
        //stopService(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "MyApp::MyWakelockTag");
        wakeLock.release();
        //stop service if we want
        //stopService(intent);
    }

    public void sendBroadcast()
    {
        Intent intent = new Intent();
        intent.setAction("com.mybroadcast.sendbroadcast");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}