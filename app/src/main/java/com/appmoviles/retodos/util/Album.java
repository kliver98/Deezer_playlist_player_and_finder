package com.appmoviles.retodos.util;

import java.math.BigInteger;

public class Album {

    private String id;
    private String cover;
    private String name;
    private String userCreator;
    private String trackList;
    private Integer nItems;

    public Album() {
    }

    public Album(String id, String cover, String name, String userCreator, String trackList, Integer nItems) {
        this.id = id;
        this.cover = cover;
        this.name = name;
        this.userCreator = userCreator;
        this.trackList = trackList;
        this.nItems = nItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getTrackList() {
        return trackList;
    }

    public void setTrackList(String trackList) {
        this.trackList = trackList;
    }

    public Integer getnItems() {
        return nItems;
    }

    public void setnItems(Integer nItems) {
        this.nItems = nItems;
    }
}
