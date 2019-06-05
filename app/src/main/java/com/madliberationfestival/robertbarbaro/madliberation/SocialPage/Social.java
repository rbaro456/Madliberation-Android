package com.madliberationfestival.robertbarbaro.madliberation.SocialPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madliberationfestival.robertbarbaro.madliberation.R;


public class Social extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        setTitle("Social");

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {  // This should never occur; but just in case

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar
        }

        // Populate ListView with social media links
        ListView socialList = findViewById(R.id.social_list);
        SocialAdapter socialAdapter = new SocialAdapter(this);
        socialList.setAdapter(socialAdapter);


    }
}
