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

    View view;
    private ListView musicList;


    ArrayList<String> platforms;
    ArrayList<String> links;

    public FragmentMusic() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.music_fragment,container,false);

        Bundle bundle = getArguments();

        String artistName = bundle.getString("ARTIST_NAME");

        DataBaseHelper db = new DataBaseHelper(getContext());
        List<ArtistMusic> artistMusic = db.getArtistMusic(artistName);
        System.out.println("SIZE OF ARTIST LIST IS :::" + artistMusic.size());


/*
        platforms = new ArrayList<>();
        links = new ArrayList<>();
        platforms.add("Spotify");
        platforms.add("Sound Cloud");
        platforms.add("Youtube");
        links.add("https://open.spotify.com/artist/4kANxfLenUobb7t5fHSrgA");
        links.add("https://soundcloud.com/moor-goddess");
        links.add("https://www.youtube.com/watch?v=asYtTRfkbn8");

*/


        musicList = view.findViewById(R.id.music_list);

        MusicAdapter adapter = new MusicAdapter(getActivity(), artistMusic);
        musicList.setAdapter(adapter);
       // ArrayAdapter<String> adapter =
        //        new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, platforms);
        //musicList.setAdapter(adapter);

        return view;
    }
}
