package com.madliberationfestival.robertbarbaro.madliberation.Model;

public class ArtistMusic {

    private String name;

    private String platform;

    private String link;

    public ArtistMusic(String name, String platform, String link) {
        this.name = name;
        this.platform = platform;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getLink() {
        return link;
    }
}
