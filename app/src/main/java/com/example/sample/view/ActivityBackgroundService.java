package com.example.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sample.R;
import com.example.sample.services.BackgroundService;

public class ActivityBackgroundService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_background_service);
        Button start_service_button = (Button) findViewById(R.id.start_service_button);
        start_service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent =  new Intent(ActivityBackgroundService.this, BackgroundService.class);
                startService(serviceIntent);
            }
        });

    }
}
