package com.appmoviles.retodos.model.search;

public class Artist {

    private String name;
    private String link;
    private String picture;
    private String tracklist;

    public Artist() {
    }

    public Artist(String name, String link, String picture, String tracklist) {
        this.name = name;
        this.link = link;
        this.picture = picture;
        this.tracklist = tracklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
