package com.madliberationfestival.robertbarbaro.madliberation.schedule;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.model.ArtistSchedule;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ScheduleListAdapter extends BaseAdapter {

    private ArrayList<Object> artistSchedules;
    private Activity activity;

    private static final int ARTIST_VIEW = 0;
    private static final int TIME_VIEW = 1;

    public ScheduleListAdapter(Activity activity, ArrayList<Object> artistSchedules) {

        this.activity = activity;
        this.artistSchedules = artistSchedules;

    }

    @Override
    public int getItemViewType(int position) {  // Returns 0 if it is Artist View and 1 if it is Time View
        if(artistSchedules.get(position) instanceof ArtistSchedule) {

            return ARTIST_VIEW;
        } else {

            return TIME_VIEW;
        }
    }

    @Override
    public int getViewTypeCount() { // Returns 2 because that is the number of different views
        return 2;                   // either time_stamp or artist_schedule
    }

    @Override
    public int getCount() {
        return artistSchedules.size();
    }

    @Override
    public Object getItem(int position) {
        return artistSchedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(getItemViewType(position) == 0) {
            ArtistSchedule artistInfo = (ArtistSchedule) artistSchedules.get(position);
            convertView = activity.getLayoutInflater().inflate(R.layout.test, null);

            TextView artistName = convertView.findViewById(R.id.artist_names);
            artistName.setText(artistInfo.getArtistName());

            TextView stage = convertView.findViewById(R.id.stage);
            stage.setText(artistInfo.getStage());

            String timeString = artistInfo.getStartTime() + " - " + artistInfo.getEndTime();

            TextView time = convertView.findViewById(R.id.times);
            time.setText(timeString);

            DataBaseHelper db = new DataBaseHelper(activity);
            String artistImage = db.getArtistImage(artistInfo.getArtistName());


            try {
                InputStream is = activity.getAssets().open(artistImage);

                Bitmap bm =  BitmapFactory.decodeStream(is);

                ImageView imageView = convertView.findViewById(R.id.artists_image);
                imageView.setImageBitmap(bm);


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            convertView = activity.getLayoutInflater().inflate(R.layout.time_stamp, null);
            TextView timeStamp = convertView.findViewById(R.id.time_stamp);
            timeStamp.setText((String) artistSchedules.get(position));
            return convertView;

        }

/*
        if(!artistSchedules.get(position).getStartTime().equals(currTime)) {
            convertView = activity.getLayoutInflater().inflate(R.layout.time_stamp, null);
            currTime = artistSchedules.get(position).getStartTime();
            TextView timeStamp = convertView.findViewById(R.id.time_stamp);
            timeStamp.setText(currTime);
            return convertView;
        }
/*
        if(position == 3) {
            convertView = activity.getLayoutInflater().inflate(R.layout.time_stamp, null);
            return convertView;
        }
*/
//        convertView = activity.getLayoutInflater().inflate(R.layout.artist_list_item, null);
  //      TextView artistName = convertView.findViewById(R.id.artist_names);
    //    artistName.setText(artistSchedules.get(position).getArtistName());

        return convertView;
    }
}
