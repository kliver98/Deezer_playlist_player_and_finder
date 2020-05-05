package com.appmoviles.retodos.model.playlist;

public class Artist {

    private String name;
    private String tracklist;

    public Artist() {
    }

    public Artist(String name, String tracklist) {
        this.name = name;
        this.tracklist = tracklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
