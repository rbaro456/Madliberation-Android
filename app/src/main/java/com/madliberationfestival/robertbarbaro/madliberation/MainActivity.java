package com.madliberationfestival.robertbarbaro.madliberation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button artistsButton = findViewById(R.id.artists_button);

        artistsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), Artists.class));

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
