package com.appmoviles.retodos.model.playlist;

public class Track {

    private Data[] data;

    public Track() {
    }

    public Track(Data[] data) {
        this.data = data;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }
}
