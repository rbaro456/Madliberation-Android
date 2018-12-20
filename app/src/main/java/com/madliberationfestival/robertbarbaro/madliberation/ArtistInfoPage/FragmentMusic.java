package com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madliberationfestival.robertbarbaro.madliberation.R;

public class FragmentMusic extends Fragment {

    View view;
    public FragmentMusic() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.music_fragment,container,false);
        return view;
    }
}
