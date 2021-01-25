package com.reliancehmotest.prozoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
int status = 0;
Context c =  MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRelianceSplash();
    }

    private void loadRelianceSplash(){

        new Thread(new Runnable() {
            public void run() {
                while (status < 100) {
                    status += 5;

                    try {

                        Thread.sleep(200);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                startActivity(new Intent(c, RelianceProZoneAppProviderActivity.class));

            }
        }).start();

    }
}