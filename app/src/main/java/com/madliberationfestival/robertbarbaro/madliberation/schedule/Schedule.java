package com.madliberationfestival.robertbarbaro.madliberation.schedule;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import com.madliberationfestival.robertbarbaro.madliberation.artistinfo.ArtistInfo;
import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.model.ArtistSchedule;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = findViewById(R.id.schedule_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {  // This should never occur; but just in case

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar
        }

        // Creates spinner to choose which day to display schedule for
        Spinner daySpinner = findViewById(R.id.day_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.spin_item);
        daySpinner.setAdapter(spinnerAdapter);
        daySpinner.setOnItemSelectedListener(this);
        //daySpinner.getBackground().setColorFilter(getResources().getColor(R.color.design_default_color_primary),
        //        PorterDuff.Mode.SRC_ATOP);



        ListView scheduleListView = findViewById(R.id.schedule);
        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                try {

                    ArtistSchedule entry = (ArtistSchedule) parent.getItemAtPosition(position);

                    Intent intent = new Intent(getBaseContext(), ArtistInfo.class);
                    intent.putExtra("ARTIST_NAME", entry.getArtistName());
                    startActivity(intent);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            }
        });



    }

    // Sets the schedule Adapter depending on the day that was selected
    // 1 for day one (Friday) and 2 for day two (Saturday)
    private void setScheduleAdapter(int daySchedule) {

        DataBaseHelper db = new DataBaseHelper(this);  // create database instance

        List<ArtistSchedule> schedule = db.getScheduleByDay(daySchedule);  // get schedule by day; 1 for day one and 2 for day two

        schedule = sortAMandPM(schedule);  // sorts the schedule so the PM artists come before the AM artists

        ArrayList<Object> artistSetTimeSchedule = createArtistTimeSchedule(schedule);  // creates schedule with
                                                                                        // respective start time headers
        //Set the adapter and places it in the list view
        ScheduleListAdapter adapter = new ScheduleListAdapter(this, artistSetTimeSchedule);
        ListView artistsList = findViewById(R.id.schedule);
        artistsList.setAdapter(adapter);
        /*
        artistsList.setOnClickListener(

                new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplication(),
                                "Position clicked " + position, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getBaseContext(), ArtistInfo.class);
                        intent.putExtra("ARTIST_NAME", schedule.get(position).getArtistName());
                        startActivity(intent);
                    }
                }
        );
        */
    }


    // Creates an array that contains the set start times and artists in the order of their respective start times
    private ArrayList<Object> createArtistTimeSchedule(List<ArtistSchedule> schedule) {

        ArrayList<Object> artistScheduleStartTime = new ArrayList<>(); // Stores the artist schedule object
        // and start time so the view can properly be
        // created

        String currTime = schedule.get(0).getStartTime();
        artistScheduleStartTime.add(currTime);  // adds first start time

        int i = 0;

        while(i < schedule.size()){  // iterates through schedule placing start time then
            // placing artists under their respective start times
            ArtistSchedule a = schedule.get(i);

            if(!a.getStartTime().equals(currTime)) {
                artistScheduleStartTime.add(a.getStartTime());
                currTime = a.getStartTime();
            } else {

                artistScheduleStartTime.add(a);
                i++;
            }
        }

        return artistScheduleStartTime;

    }

    // This function sorts artists that start in the AM and PM separately so the AM artists can
    // be placed after the PM artists. This is done because each day of the festival rolls into the
    // next day
    private List<ArtistSchedule> sortAMandPM(List<ArtistSchedule> schedule) {

        List<ArtistSchedule>  amArtists = new ArrayList<>();
        List<ArtistSchedule>  pmArtists = new ArrayList<>();


        for(ArtistSchedule a : schedule) {  // go through each artist and separate them by AM and PM

            String getAMorPM = a.getStartTime().split(" ")[1];

            if(getAMorPM.equals("AM")) {

                amArtists.add(a);

            } else {

                pmArtists.add(a);
            }

        }

        Collections.sort(amArtists);  // sorts AM artists by start time, using comparable in ArtistSchedule class
        Collections.sort(pmArtists);  // sorts PM artists by start time, using comparable in ArtistSchedule class

        pmArtists.addAll(amArtists);  // Places sorted AM artists after sorted PM artist


        return pmArtists;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {  // If Friday is selected, display Friday schedule

            setScheduleAdapter(1);

        } else {  // If Saturday is selected, display Saturday schedule

            setScheduleAdapter(2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
