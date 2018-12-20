package com.madliberationfestival.robertbarbaro.madliberation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        setTitle("Schedule");

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);

        String[] artists = {"Public Warfare", "8:30 pm", "Reebith", "Meelday", "Vomit Cord", "Nera"};


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artists);

        View view = View.inflate(this, R.layout.activity_main, null);


        ListView artistsList = findViewById(R.id.schedule);
        artistsList.addHeaderView(view);
        artistsList.setAdapter(adapter);

    }
}
