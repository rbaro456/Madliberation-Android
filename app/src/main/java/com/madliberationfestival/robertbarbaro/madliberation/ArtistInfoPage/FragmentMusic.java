package com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madliberationfestival.robertbarbaro.madliberation.DataBaseHelper;
import com.madliberationfestival.robertbarbaro.madliberation.Model.ArtistMusic;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMusic extends Fragment {

    public FragmentMusic() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.music_fragment,container,false);

        // Get artist name from bundle passed by ArtistInfo
        Bundle bundle = getArguments();
        String artistName = bundle.getString("ARTIST_NAME");

        // Get artist's music from database
        DataBaseHelper db = new DataBaseHelper(getContext());
        List<ArtistMusic> artistMusic = db.getArtistMusic(artistName);

        // Display artist's music in ListView
        ListView musicList = view.findViewById(R.id.music_list);
        MusicAdapter adapter = new MusicAdapter(getActivity(), artistMusic);
        musicList.setAdapter(adapter);

        return view;
    }
}
