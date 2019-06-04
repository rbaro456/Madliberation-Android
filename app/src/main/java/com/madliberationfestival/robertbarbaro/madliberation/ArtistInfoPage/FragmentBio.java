package com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage;

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

    View view;
    TextView bioText;

    public FragmentBio() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bio_fragment,container,false);

        Bundle bundle = getArguments();

        String artistName = bundle.getString("ARTIST_NAME");

        DataBaseHelper db = new DataBaseHelper(getContext());
        String artistBio = db.getArtistBio(artistName);


        bioText = view.findViewById(R.id.biotext);
        bioText.setText(artistBio);
        bioText.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
