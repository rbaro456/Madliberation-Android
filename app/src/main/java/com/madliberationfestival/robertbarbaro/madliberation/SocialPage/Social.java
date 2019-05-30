package com.madliberationfestival.robertbarbaro.madliberation.SocialPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madliberationfestival.robertbarbaro.madliberation.R;


public class Social extends AppCompatActivity {

    private ListView socialList;

    String[] socialArr = {"Insta", "Facey", "Twity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        setTitle("Social");

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar


        socialList = findViewById(R.id.social_list);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,socialArr);

        SocialAdapter socialAdapter = new SocialAdapter(this);
        socialList.setAdapter(socialAdapter);


    }
}
