package com.madliberationfestival.robertbarbaro.madliberation;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.madliberationfestival.robertbarbaro.madliberation.ArtistsPage.ArtistsActivity;
import com.madliberationfestival.robertbarbaro.madliberation.Schedule.Schedule;
import com.madliberationfestival.robertbarbaro.madliberation.SocialPage.Social;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHelper db = new DataBaseHelper(this);

        // Try to create database if it does not exist
        try {

            db.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            db.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }



        // FOR TESTING STORING IMAGES.. STORE IMAGES BY NAME
        /*
        try {
           InputStream is = getAssets().open("moor.jpg");

            Bitmap bm =  BitmapFactory.decodeStream(is);

            ImageView imageView =findViewById(R.id.imageTest);
            imageView.setImageBitmap(bm);


        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        // db.getInstance(getBaseContext());


        Button artistsButton = findViewById(R.id.artists_button);
        artistsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ArtistsActivity.class));

            }
        });

        Button scheduleButton = findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Schedule.class));

            }
        });

        Button socialButton = findViewById(R.id.social_button);
        socialButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Social.class));

            }
        });

    }
}
