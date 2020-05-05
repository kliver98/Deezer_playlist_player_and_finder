package com.appmoviles.retodos.model.search;

public class Data {

    private String title;
    private String link;
    private String duration;
    private Artist artist;
    private Album album;

    public Data() {
    }

    public Data(String title, String link, String duration, Artist artist, Album album) {
        this.title = title;
        this.link = link;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
