package com.madliberationfestival.robertbarbaro.madliberation.artists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.madliberationfestival.robertbarbaro.madliberation.artistinfo.ArtistInfo;
import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.model.Artist;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.util.List;


public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        setTitle("Artists");

        Toolbar toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {  // This should never occur; but just in case

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar
        }

        ListView artistsListView = findViewById(R.id.artists);  // Get ListView which holds all the artists

        DataBaseHelper db = new DataBaseHelper(this);  // Call database to retrieve artist data
        final List<Artist> artistsList = db.getArtists();

        // Set adapter to populate the artist data
        ArtistsListAdapter adapter = new ArtistsListAdapter(this, artistsList);
        artistsListView.setAdapter(adapter);

        // When artist is clicked it will start the ArtistInfo Activity, displaying information
        // for that specific artist
        artistsListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ArtistsActivity.this, ArtistInfo.class);
                        intent.putExtra("ARTIST_NAME", artistsList.get(position).getArtistName());
                        startActivity(intent);
                    }
                }
        );
    }
}
