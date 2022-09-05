package com.example.wakelock3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Instantiating MyBroadcastReceiver should allow us access to the
         * onReceive method within our onCreate of MainActivity2.java
         */
        IntentFilter intentFilter = new IntentFilter("com.mybroadcast.sendbroadcast");
        MyBroadcastReceiver myReceiver = new MyBroadcastReceiver();
        registerReceiver(myReceiver, intentFilter);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}