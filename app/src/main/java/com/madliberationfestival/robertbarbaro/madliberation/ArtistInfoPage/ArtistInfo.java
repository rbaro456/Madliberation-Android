package com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.madliberationfestival.robertbarbaro.madliberation.R;

public class ArtistInfo extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);

        tabLayout = findViewById(R.id.tab_layout_id);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentBio(), "Bio");
        adapter.AddFragment(new FragmentMusic(), "Music");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tabby = tabLayout.getTabAt(0);  // Set Tab Names
        tabby.setText("Bio");
        tabby = tabLayout.getTabAt(1);
        tabby.setText("Music");

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        String artistName = getIntent().getStringExtra("ARTIST_NAME");

        ImageView artistImage = findViewById(R.id.artists_image);
/*
        try
        {
            // get input stream
            InputStream ims = getAssets().open("moor.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            artistImage.setImageDrawable(d);
            ims.close();
        }
        catch(Exception ex)
        {
            return;
        }

        TextView artistNameText = findViewById(R.id.artist_name);

        artistNameText.setText(artistName);

        TextView artistBio = findViewById(R.id.artist_bio);

        artistBio.setText("" +
                "biokdk;adsjf;alejfpoiaejfefjaejfaepjfajefpjaepfjaefj" +
                "paejifpoaejfoapjefoapjefoapjefpjaepofjapoejfaefjpaejfpaefjpaejf" +
                "paesjifpioaejfpaejfpoajefpajefpjaepofjapoefjaopejfopajefp" +
                "apoejfpoieajfieaopjpaaaaaaaaaaaaaaaaaaaaaaaaaaaaaajejejep" +
                "ejfjaejppppppppppppppppppp");

                */


    }

}
