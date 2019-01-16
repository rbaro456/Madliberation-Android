package com.madliberationfestival.robertbarbaro.madliberation.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ArtistSchedule implements Comparable<ArtistSchedule>{

    private String artistName;

    private String stage;

    private String startTime;

    private String endTime;

    private int day;  // 1 for day one and 2 for day two

    public ArtistSchedule(String artistName, String stage, String startTime, String endTime, int day) {
        this.artistName = artistName;
        this.stage = stage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }


    public String getArtistName() {
        return artistName;
    }

    public String getStage() {
        return stage;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getDay() {
        return day;
    }

    public int compareTo(ArtistSchedule artist1) {

        try {
            return new SimpleDateFormat("hh:mm a", Locale.US).parse(this.getStartTime()).compareTo(new SimpleDateFormat("hh:mm a", Locale.US).parse(artist1.getStartTime()));
        } catch (ParseException e) {
            return 0;
        }

    }
}
