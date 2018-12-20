package com.madliberationfestival.robertbarbaro.madliberation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage.ArtistInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Artists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        setTitle("Artists");

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        setSupportActionBar(toolbar);

        final String[] artists = getArtists();

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artists);

        ListView artistsList = findViewById(R.id.artists);

        artistsList.setAdapter(adapter);


        artistsList.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getApplication(),
                                "Position clicked " + position, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getBaseContext(), ArtistInfo.class);
                        intent.putExtra("ARTIST_NAME", artists[position]);
                        startActivity(intent);
                    }
                }



        );
    }

    public String[] getArtists() {

        String[] artists = new String[42];

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("ArtistList.txt"), "UTF-8"));

            String line;
            int i = 0;

            while ((line = reader.readLine()) != null) {

                artists[i] = line;

                i++;
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }

        return artists;
    }


}
