package com.appmoviles.retodos.model;

import java.io.Serializable;

public class SearchItem {

    private String cover;
    private String name;
    private String username;
    private String nSongs;

    public SearchItem() {
    }

    public SearchItem(String cover, String name, String username, String nSongs) {
        this.cover = cover;
        this.name = name;
        this.username = username;
        this.nSongs = nSongs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setnSongs(String nSongs) {
        this.nSongs = nSongs;
    }

    public String getCover() {
        return cover;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getnSongs() {
        return nSongs;
    }
}
