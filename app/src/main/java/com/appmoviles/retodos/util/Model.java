package com.appmoviles.retodos.util;

import android.util.Log;

import com.appmoviles.retodos.model.search.Data;
import com.appmoviles.retodos.model.search.Deezer;
import com.google.gson.Gson;

import java.util.HashMap;

public class Model implements HTTPSWebUtilDomi.OnResponseListener {

    private com.appmoviles.retodos.model.search.Deezer deezer;

    private HashMap<String,Integer> aux;
    private HashMap<String, Album> albums;
    private HTTPSWebUtilDomi utilDomi;

    public static class AlbumTMP {
        private String id;
        private String title;
        private int nb_tracks;

        public AlbumTMP() {
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

        public int getNb_tracks() {
            return nb_tracks;
        }

        public void setNb_tracks(int nb_tracks) {
            this.nb_tracks = nb_tracks;
        }
    }

    public Model(com.appmoviles.retodos.model.search.Deezer dSearch) {
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        deezer = dSearch;
        aux = new HashMap<String, Integer>();
    }

    @Override
    public void onResponse(int callbackID, String response) {
        if (response.equals("{\"error\":{\"type\":\"Exception\",\"message\":\"Quota limit exceeded\",\"code\":4}}"))
            return;
        Gson g = new Gson();
        AlbumTMP t = g.fromJson(response,AlbumTMP.class);
        int i = t.getNb_tracks();
        aux.put(t.getTitle(),i);
    }

    public HashMap<String, Album> getAlbums() {
        albums = new HashMap<String,Album>();
        for (Data d: deezer.getData()) {
            com.appmoviles.retodos.model.search.Album a = d.getAlbum();
            if (albums.containsKey(a.getTitle()))
                continue;
            Album created = new Album();
            created.setCover(a.getCover());
            created.setName(a.getTitle());
            created.setTrackList(a.getTracklist());
            created.setUserCreator(d.getArtist().getName());
            created.setId(a.getId());
            albums.put(a.getTitle(), created);
            aux.put(created.getName(),0);
        }
        return albums;
    }

    public HashMap<String, Album> chargeNTracks() {
        Thread t = new Thread(
                () -> {
                    for (String k: albums.keySet()) {
                        String url = "https://api.deezer.com/album/" + albums.get(k).getId();
                        utilDomi.GETrequest(-1, url);
                    }
                }
        );
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String k: albums.keySet()) {
            com.appmoviles.retodos.util.Album a = albums.get(k);
            a.setnItems(aux.get(k));
        }
        return albums;
    }

    public Album getAlbum(String key) {
        return albums.get(key);
    }

    public Deezer getDeezer() {
        return deezer;
    }

    public void setDeezer(Deezer deezer) {
        this.deezer = deezer;
    }

    public HashMap<String, Integer> getAux() {
        return aux;
    }

    public void setAux(HashMap<String, Integer> aux) {
        this.aux = aux;
    }

    public void setAlbums(HashMap<String, Album> albums) {
        this.albums = albums;
    }

    public HTTPSWebUtilDomi getUtilDomi() {
        return utilDomi;
    }

    public void setUtilDomi(HTTPSWebUtilDomi utilDomi) {
        this.utilDomi = utilDomi;
    }
}
