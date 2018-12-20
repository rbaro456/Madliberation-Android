package com.madliberationfestival.robertbarbaro.madliberation;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistsListAdapter extends BaseAdapter {

    String[] arr;

    Activity activity;

    public ArtistsListAdapter(Activity activity, String[] artists) {

        this.activity = activity;
        arr = artists;
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return arr[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = activity.getLayoutInflater().inflate(R.layout.artist_list_item, null);

        TextView artistName = convertView.findViewById(R.id.artist_names);

        artistName.setText(arr[position]);

        return convertView;
    }
}
