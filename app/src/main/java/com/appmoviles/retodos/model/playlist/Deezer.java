package com.appmoviles.retodos.model.playlist;

public class Deezer {

    private String title;
    private String cover;
    private String cover_big;
    private String label;
    private int nb_tracks;
    private Track tracks;

    public Deezer() {
    }

    public Deezer(String title, String cover, String cover_big, String label, int nb_tracks, Track tracks) {
        this.title = title;
        this.cover = cover;
        this.cover_big = cover_big;
        this.label = label;
        this.nb_tracks = nb_tracks;
        this.tracks = tracks;
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

    public String getCover_big() {
        return cover_big;
    }

    public void setCover_big(String cover_big) {
        this.cover_big = cover_big;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public Track getTracks() {
        return tracks;
    }

    public void setTracks(Track tracks) {
        this.tracks = tracks;
    }
}
