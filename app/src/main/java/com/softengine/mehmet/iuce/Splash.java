package com.softengine.mehmet.iuce;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                startActivity1();
            }
        };
        Handler handler=new Handler();
        handler.postDelayed(runnable,1000);
    }

    private void startActivity1() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
