package com.example.gamesolitarycelta_v10;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int time = 500;
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(i);
        }, time);
    }
}