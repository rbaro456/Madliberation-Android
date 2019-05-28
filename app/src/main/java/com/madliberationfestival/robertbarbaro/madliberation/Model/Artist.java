package com.madliberationfestival.robertbarbaro.madliberation.Model;

public class Artist {

    private String artistName;

    private String bio;

    private String image;

    public Artist(String artistName, String bio, String image) {

        this.artistName = artistName;
        this.bio = bio;
        this.image = image;
    }


    public String getArtistName() {
        return artistName;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
