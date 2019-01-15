package com.madliberationfestival.robertbarbaro.madliberation;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.madliberationfestival.robertbarbaro.madliberation.Model.ArtistSchedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Schedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<ArtistSchedule> schedule;  // Array of each artist's scheduled set time



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setTitle("Schedule");

        //Toolbar toolbar = findViewById(R.id.app_bar);
       // toolbar.setTitleTextColor(0xFFFFFFFF);

       // setSupportActionBar(toolbar);

        Spinner daySpinner = findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(spinnerAdapter);
        daySpinner.setOnItemSelectedListener(this);





       // schedule = readSchedule();  // reads and stores schedule data from csv

       // setSchedule(1);



    }

    private void setSchedule(int daySchedule) {
        schedule = new ArrayList<>();

        Cursor cursor = MainActivity.myDbHelper.meow(daySchedule);

        if (cursor.moveToFirst()){
            do{
                String artist = cursor.getString(cursor.getColumnIndex("artist"));
                String stage = cursor.getString(cursor.getColumnIndex("stage"));
                String start = cursor.getString(cursor.getColumnIndex("start"));
                String end = cursor.getString(cursor.getColumnIndex("end"));
                int day = cursor.getInt(cursor.getColumnIndex("day"));

                ArtistSchedule artistSchedule = new ArtistSchedule(artist, stage, start, end, day);
                schedule.add(artistSchedule);

            }while(cursor.moveToNext());
        }
        cursor.close();

        schedule = sortAMandPM(schedule);

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

        ScheduleListAdapter adapter = new ScheduleListAdapter(this, artistScheduleStartTime);

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

            br.readLine(); // Used to omit first line of CSV
                            // since it only contains headers

            // If buffer is not empty
            while ((line = br.readLine()) != null) {
                // use comma as separator columns of CSV
                String[] cols = line.split(",");


               // ArtistSchedule artistSchedule = new ArtistSchedule(cols[0], cols[1],
               //         cols[2], cols[3]);

             //   artistSchedules.add(artistSchedule);

            }
        } catch (IOException e) {
            // Prints throwable details
            e.printStackTrace();
        }

        artistSchedules = sortAMandPM(artistSchedules);

        return artistSchedules;

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

        if (position == 0) {

            setSchedule(1);
        } else {
            setSchedule(2);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
