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

import com.madliberationfestival.robertbarbaro.madliberation.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter {

    private ArrayList<String> platforms;
    private ArrayList<String> links;

    private Activity activity;

    public MusicAdapter(Activity activity, ArrayList<String> platforms, ArrayList<String> links) {

        this.platforms = platforms;
        this.activity = activity;
        this.links = links;
    }

    @Override
    public int getCount() {
        return platforms.size();
    }

    @Override
    public Object getItem(int position) {
        return platforms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String plat = platforms.get(position);
        convertView = activity.getLayoutInflater().inflate(R.layout.music_item, null);
        TextView textView = convertView.findViewById(R.id.music_text);

        String link = "<a href=\""+links.get(position)+"\">"+plat+"</a>";

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
