package com.appmoviles.retodos.model.playlist;

public class Data {

    private String title;
    private String link;
    private Artist artist;
    private String duration;
    private String preview;

    public Data() {
    }

    public Data(String title, String link, Artist artist, String duration, String preview) {
        this.title = title;
        this.link = link;
        this.artist = artist;
        this.duration = duration;
        this.preview = preview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
