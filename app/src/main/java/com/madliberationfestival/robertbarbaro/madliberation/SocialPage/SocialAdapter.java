package com.madliberationfestival.robertbarbaro.madliberation.SocialPage;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.R;

public class SocialAdapter extends BaseAdapter {


    private Context context;
    private String[] socialArr = {"Facebook", "Instagram", "Twitter"};
    private String[] links = {"https://www.facebook.com/MADLIBERATION/",
    "https://www.instagram.com/madlibfest/?hl=en",
    "https://twitter.com/madlibfest?lang=en"};

    public SocialAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return socialArr.length;
    }

    @Override
    public Object getItem(int position) {
        return socialArr[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.social_item, parent, false);
        String social = socialArr[position];

        String link = "<a href=\""+links[position]+"\">"+social+"</a>";

        Spannable s = (Spannable) Html.fromHtml(link);  // Removes underline from links
        for (URLSpan u: s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }


        TextView textView = convertView.findViewById(R.id.social_text);

        textView.setText(s);


        return convertView;
    }
}
