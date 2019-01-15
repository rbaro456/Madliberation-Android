package com.madliberationfestival.robertbarbaro.madliberation;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static DataBaseHelper myDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }


        //  TESTING FOR CALLING FROM DATABASE!!
        Cursor cursor = myDbHelper.meow(1);

        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(cursor.getColumnIndex("artist"));
                Toast.makeText(this, data ,
                        Toast.LENGTH_LONG).show();
            }while(cursor.moveToNext());
        }
        cursor.close();


        // FOR TESTING STORING IMAGES.. STORE IMAGES BY NAME
        try {
           InputStream is = getAssets().open("moor.jpg");

            Bitmap bm =  BitmapFactory.decodeStream(is);

            ImageView imageView =findViewById(R.id.imageTest);
            imageView.setImageBitmap(bm);


        } catch (IOException e) {
            e.printStackTrace();
        }


        // db.getInstance(getBaseContext());

        Button artistsButton = findViewById(R.id.artists_button);

        artistsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), ArtistsActivity.class));

            }
        });

        Button scheduleButton = findViewById(R.id.schedule_button);

        scheduleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), Schedule.class));

            }
        });
    }
}
