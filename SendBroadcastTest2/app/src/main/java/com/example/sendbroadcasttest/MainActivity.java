package com.example.sendbroadcasttest;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button = (Button) findViewById(R.id.SendBroadcast);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Send the broadcast from another app to receive at this receiver
                //We can code another app to send this broadcast with this tag

                Intent intent = new Intent();
                intent.setAction("com.mybroadcast.sendbroadcast");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);

            }
        });
    }


}