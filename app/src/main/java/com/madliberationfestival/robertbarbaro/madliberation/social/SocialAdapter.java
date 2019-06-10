package com.madliberationfestival.robertbarbaro.madliberation.social;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.madliberationfestival.robertbarbaro.madliberation.R;

public class SocialAdapter extends BaseAdapter {


    private Context context; // Holds the Context

    // Holds the name of social media platforms
    private final String[] socialArr = {"Facebook", "Instagram", "Twitter"};

    // Holds the links to each social media platform
    private final String[] links = {"https://www.facebook.com/MADLIBERATION/",
    "https://www.instagram.com/madlibfest/?hl=en",
    "https://twitter.com/madlibfest?lang=en"};

    // Holds the icon for each social media platform
    private final int[] drawableImages = {R.drawable.facebook, R.drawable.instagram, R.drawable.twitter};

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

        Bitmap myLogo = BitmapFactory.decodeResource(context.getResources(), drawableImages[position]);

        ImageView socialImage = convertView.findViewById(R.id.social_image);
        socialImage.setImageBitmap(myLogo);

        String social = socialArr[position]; // Get appropriate social media name
        String link = "<a href=\""+links[position]+"\">"+social+"</a>";  // Get appropriate social media link
                                                                        // and convert it into a link

        // Removes underline from link
        Spannable s = (Spannable) Html.fromHtml(link);
        for (URLSpan u: s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }

        // Set the TextView to be the clickable link
        TextView textView = convertView.findViewById(R.id.social_text);
        textView.setText(s);
        textView.setMovementMethod(LinkMovementMethod.getInstance());  // Allows links to be clickable



        return convertView;
    }
}
