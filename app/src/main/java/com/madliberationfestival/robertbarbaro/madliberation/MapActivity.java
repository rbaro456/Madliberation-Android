package com.madliberationfestival.robertbarbaro.madliberation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setTitle("Map");

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {  // This should never occur; but just in case

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar
        }


    }
}
