package com.madliberationfestival.robertbarbaro.madliberation.artistinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.R;

public class FragmentBio extends Fragment {

    public FragmentBio() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bio_fragment,container,false);

        // Get artist name from bundle passed by ArtistInfo
        Bundle bundle = getArguments();
        String artistName = bundle.getString("ARTIST_NAME");

        // Get artist's bio from database
        DataBaseHelper db = new DataBaseHelper(getContext());
        String artistBio = db.getArtistBio(artistName);

        // Set artist's bio in the bio text fragment
        TextView bioText = view.findViewById(R.id.biotext);
        bioText.setText(artistBio);
        bioText.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
