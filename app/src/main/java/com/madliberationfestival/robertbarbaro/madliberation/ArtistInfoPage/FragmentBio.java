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

        bioText = view.findViewById(R.id.biotext);
        bioText.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}
