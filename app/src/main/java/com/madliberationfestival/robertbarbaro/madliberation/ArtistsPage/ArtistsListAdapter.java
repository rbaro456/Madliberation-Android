package com.madliberationfestival.robertbarbaro.madliberation.ArtistsPage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.Model.Artist;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArtistsListAdapter extends BaseAdapter {

    List<Artist> artistsList;

    Activity activity;

    public ArtistsListAdapter(Activity activity, List<Artist> artistsList) {

        this.activity = activity;
        this.artistsList = artistsList;
    }

    @Override
    public int getCount() {
        return artistsList.size();
    }

    @Override
    public Object getItem(int position) {
        return artistsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = activity.getLayoutInflater().inflate(R.layout.artist_list_item, null);

        TextView artistName = convertView.findViewById(R.id.artist_names);

       // ImageView artistImage = convertView.findViewById(R.id.artists_image);

        try {

            // SHOULD I USE CONTEXT INSTEAD OF ACTIVITY?????????
            InputStream is = activity.getAssets().open(artistsList.get(position).getImage());

            Bitmap bm =  BitmapFactory.decodeStream(is);

            ImageView imageView = convertView.findViewById(R.id.artists_image);
            imageView.setImageBitmap(bm);


        } catch (IOException e) {
            e.printStackTrace();
        }

        artistName.setText(artistsList.get(position).getArtistName());

        return convertView;
    }
}
