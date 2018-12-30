package com.madliberationfestival.robertbarbaro.madliberation.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ArtistSchedule implements Comparable<ArtistSchedule>{


    private String artistName;

    private String stage;

    private String startTime;

    private String endTime;

    public ArtistSchedule(String artistName, String stage, String startTime, String endTime) {
        this.artistName = artistName;
        this.stage = stage;
        this.startTime = startTime;
        this.endTime = endTime;
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


    public int compareTo(ArtistSchedule artist1) {

        try {
            return new SimpleDateFormat("hh:mm a", Locale.US).parse(this.getStartTime()).compareTo(new SimpleDateFormat("hh:mm a", Locale.US).parse(artist1.getStartTime()));
        } catch (ParseException e) {
            return 0;
        }

    }
}
