package com.madliberationfestival.robertbarbaro.madliberation.artistinfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.model.Artist;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.io.IOException;
import java.io.InputStream;

public class ArtistInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_infotest);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {  // This should never occur; but just in case

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Adds up navigation arrow to tool bar
        }


        String artistName = getIntent().getStringExtra("ARTIST_NAME");  // Get artist name that was clicked

        setTitle(artistName);  // Set title to name of artist

        // Get artist information from database
        DataBaseHelper db = new DataBaseHelper(this);
        Artist artist = db.getArtist(artistName);

        TabLayout tabLayout = findViewById(R.id.tab_layout_id);
        ViewPager viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();  // Create bundle to pass artist name to bio fragment and music fragment
        bundle.putString("ARTIST_NAME", artistName);

        // Create fragment instance and pass artist name in bundle
        // Need to pass name so the bio and music of that specific artist can be queried from database
        FragmentMusic musicFrag = new FragmentMusic();
        musicFrag.setArguments(bundle);

        FragmentBio bioFrag = new FragmentBio();
        bioFrag.setArguments(bundle);

        // Add fragment to the adapter
        adapter.AddFragment(bioFrag);
        adapter.AddFragment(musicFrag);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Set appropriate tab titles
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.setText("Bio");
        tab = tabLayout.getTabAt(1);
        tab.setText("Music");



//        ImageView artistImage =findViewById(R.id.artist_info_image);
        String imag = artist.getImage();
        imag = imag.substring(0,imag.indexOf('.'));
        ImageView imageView = findViewById(R.id.artist_info_image);
        imageView.setImageResource(this.getResources().getIdentifier(imag,
                "drawable", this.getPackageName()));




    }

    // Overrides the up navigation to work like the android back button on phone
    // This is used to go back to the calling activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
