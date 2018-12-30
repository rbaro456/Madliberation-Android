package com.madliberationfestival.robertbarbaro.madliberation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;


import com.madliberationfestival.robertbarbaro.madliberation.Model.ArtistSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Schedule extends AppCompatActivity {

    List<ArtistSchedule> schedule;  // Array of each artist's scheduled set time



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setTitle("Schedule");

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);

        schedule = readSchedule();  // reads and stores schedule data from csv


        Collections.sort(schedule);  // sort schedule by time; uses comparable in ArtistSchedule class

        String currTime = schedule.get(0).getStartTime();
        ArrayList<Object> meow = new ArrayList<>();

        int i = 0;

        while(i < schedule.size()){

            ArtistSchedule a = schedule.get(i);

            if(!a.getStartTime().equals(currTime)) {
                meow.add(a.getStartTime());
                currTime = a.getStartTime();
            } else {

                meow.add(a);
                i++;
            }
        }





        String[] artists = {"Public Warfare", "Reebith", "Meelday", "Vomit Cord", "Nera"};  // FOR TESTING!!


      //  ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artists);

        ScheduleListAdapter adapter = new ScheduleListAdapter(this, meow);

        ListView artistsList = findViewById(R.id.schedule);
        artistsList.setAdapter(adapter);

    }

    private List<ArtistSchedule> readSchedule() {

        List<ArtistSchedule> artistSchedules = new ArrayList<>();

        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.schedule);

        // Reads text from character-input stream, buffering characters for efficient reading
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        // Initialization
        String line = "";

        // Handling exceptions
        try {
            // If buffer is not empty
            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");


                ArtistSchedule artistSchedule = new ArtistSchedule(cols[0], cols[1],
                        cols[2], cols[3]);

                artistSchedules.add(artistSchedule);

            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }

        return artistSchedules;

    }




}
