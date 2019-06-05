package com.madliberationfestival.robertbarbaro.madliberation.ArtistInfoPage;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.Model.ArtistMusic;
import com.madliberationfestival.robertbarbaro.madliberation.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends BaseAdapter {

    private List<ArtistMusic> artistMusic;

    private Activity activity;

    public MusicAdapter(Activity activity, List<ArtistMusic> artistMusic) {

        this.activity = activity;
        this.artistMusic = artistMusic;

    }

    @Override
    public int getCount() {
        return artistMusic.size();
    }

    @Override
    public Object getItem(int position) {
        return artistMusic.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String plat = artistMusic.get(position).getPlatform();
        convertView = activity.getLayoutInflater().inflate(R.layout.music_item, null);
        TextView textView = convertView.findViewById(R.id.music_text);

        String link = "<a href=\""+artistMusic.get(position).getLink()+"\">"+plat+"</a>";

        Spannable s = (Spannable) Html.fromHtml(link);  // Removes underline from links
        for (URLSpan u: s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }

        textView.setText(s);
        textView.setMovementMethod(LinkMovementMethod.getInstance());  // Allows links to be clickable

        return convertView;
    }
}
