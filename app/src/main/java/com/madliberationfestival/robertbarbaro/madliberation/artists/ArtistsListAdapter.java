package com.madliberationfestival.robertbarbaro.madliberation.artists;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.model.Artist;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArtistsListAdapter extends BaseAdapter {

    private List<Artist> artistsList;  // Holds the list of Artists

    private Activity activity;  // Holds calling Activity

    private List<String> imagenames;

    public ArtistsListAdapter(Activity activity, List<Artist> artistsList) {

        this.activity = activity;
        this.artistsList = artistsList;
        imagenames = new ArrayList<>();

        for(Artist a : artistsList) {

           // String imag = artistsList.get(position).getImage();
            String imag = a.getImage();
            imag = imag.substring(0,imag.indexOf('.'));
            imagenames.add(imag);
        }

        Log.d("AristList Created", "been created");
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

    class MyViewHolder {

        TextView artistName;
        ImageView imageView;

        MyViewHolder(View v) {
            artistName = v.findViewById(R.id.artist_names);
            imageView = v.findViewById(R.id.artists_image);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        MyViewHolder holder = null;
        if(row == null) {
            row = activity.getLayoutInflater().inflate(R.layout.artist_list_item, parent, false);

            holder = new MyViewHolder(row);
            row.setTag(holder);
        } else {

            holder = (MyViewHolder) row.getTag();
        }
        // Set the artist name in TextView
       // TextView artistName = row.findViewById(R.id.artist_names);
        holder.artistName.setText(artistsList.get(position).getArtistName());


        // Get appropriate artist image and display it in ImageView

       // try {

            // Retrieve the artist's image by name
            /*
            System.out.println("ARTISTS IMAGEEE!!! ::: " + artistsList.get(position).getImage());
            InputStream is = activity.getAssets().open(artistsList.get(position).getImage());
            Bitmap bm =  BitmapFactory.decodeStream(is);

            // Set the image in the ImageView
           // ImageView imageView = row.findViewById(R.id.artists_image);
            holder.imageView.setImageBitmap(bm);
            */


           // String imag = artistsList.get(position).getImage();
          //  imag = imag.substring(0,imag.indexOf('.'));
            holder.imageView.setImageResource(activity.getResources().getIdentifier(imagenames.get(position),
                    "drawable", activity.getPackageName()));


      //  } catch (IOException e) {
      //      e.printStackTrace();
      //  }

        return row;
    }
}
