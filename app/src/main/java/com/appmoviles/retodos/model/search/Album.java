package com.appmoviles.retodos.model.search;

public class Album {

    private String id;
    private String title;
    private String cover;
    private String tracklist;

    public Album() {
    }

    public Album(String id, String title, String cover, String tracklist) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.tracklist = tracklist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
