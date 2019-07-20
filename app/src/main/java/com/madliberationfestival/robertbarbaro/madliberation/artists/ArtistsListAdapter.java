package com.madliberationfestival.robertbarbaro.madliberation.artists;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.model.Artist;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArtistsListAdapter extends BaseAdapter {

    private List<Artist> artistsList;  // Holds the list of Artists

    private Activity activity;  // Holds calling Activity

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

        // Set the artist name in TextView
        TextView artistName = convertView.findViewById(R.id.artist_names);
        artistName.setText(artistsList.get(position).getArtistName());


        // Get appropriate artist image and display it in ImageView
        try {

            // Retrieve the artist's image by name
            System.out.println("ARTISTS IMAGEEE!!! ::: " + artistsList.get(position).getImage());
            InputStream is = activity.getAssets().open(artistsList.get(position).getImage());
            Bitmap bm =  BitmapFactory.decodeStream(is);

            // Set the image in the ImageView
            ImageView imageView = convertView.findViewById(R.id.artists_image);
            imageView.setImageBitmap(bm);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
