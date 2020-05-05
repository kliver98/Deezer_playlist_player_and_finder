package com.appmoviles.retodos.model.search;

public class Deezer {

    private Data[] data;
    private String total;
    private String next;

    public Deezer() {
    }

    public Deezer(Data[] data, String total, String next) {
        this.data = data;
        this.total = total;
        this.next = next;
    }

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
