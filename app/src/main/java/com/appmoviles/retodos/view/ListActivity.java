package com.appmoviles.retodos.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appmoviles.retodos.R;
import com.appmoviles.retodos.model.SearchItem;
import com.appmoviles.retodos.model.SearchItemAdapter;
import com.appmoviles.retodos.util.Constants;
import com.appmoviles.retodos.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements HTTPSWebUtilDomi.OnResponseListener {

    private RecyclerView recyclerViewSongs;
    private SearchItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int positionClicked;
    private ArrayList<SearchItem> items;
    private HTTPSWebUtilDomi utilDomi;
    private com.appmoviles.retodos.model.playlist.Deezer deezer;

    private ImageView backArrowIV;
    private ImageView listIV;
    private TextView listNameTV;
    private TextView descriptionTV;
    private TextView extraTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        initialize();
        String url = getIntent().getExtras().getString("url").split("/tracks")[0];
        Thread t = new Thread(
                () -> {
                    utilDomi.GETrequest(Constants.PLAYLIST_CALLBACK,url);
                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    private void initialize() {
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        descriptionTV = findViewById(R.id.descriptionTV);
        descriptionTV.setMovementMethod(ScrollingMovementMethod.getInstance());

        items = new ArrayList<>();
        buildRecycleView();
        backArrowIV = findViewById(R.id.backArrowIV);
        backArrowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        listIV = findViewById(R.id.listIV);
        listNameTV = findViewById(R.id.listNameTV);
        extraTV = findViewById(R.id.extraTV);
    }

    private void openMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        this.startActivity(i);
        this.finish();
    }

    private void buildRecycleView() {
        recyclerViewSongs = findViewById(R.id.recyclerViewSongs);
        recyclerViewSongs.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SearchItemAdapter(items);

        recyclerViewSongs.setLayoutManager(layoutManager);
        recyclerViewSongs.setAdapter(adapter);

        adapter.setOnItemClickListener(new SearchItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                positionClicked = position;
                openSongActivity();
            }
        });
    }

    private void setData() {
        String cover = deezer.getCover();
        listNameTV.setText(deezer.getTitle());
        descriptionTV.setText(deezer.getLabel());
        extraTV.setText("# de canciones: "+deezer.getNb_tracks());
        for(com.appmoviles.retodos.model.playlist.Data track: deezer.getTracks().getData()) {
            SearchItem item = new SearchItem();
            item.setName(track.getTitle());
            item.setCover(cover);
            item.setUsername(track.getArtist().getName());
            item.setnSongs(convertTime(track.getDuration())+" minutos");
            items.add(item);
        }
    }

    public static String convertTime(String seconds) {
        int t = Integer.parseInt(seconds);
        double aux = t/60.0;
        int m = (int)aux;
        int s = (int) ((aux-m)*60);
        return m+":"+(s<10 ? "0"+s:s);
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case Constants.PLAYLIST_CALLBACK:
                Gson g = new Gson();
                deezer = g.fromJson(response,com.appmoviles.retodos.model.playlist.Deezer.class);
                setData();
                break;
        }
    }

    private void openSongActivity() {
        Intent i = new Intent(this,SongActivity.class);
        com.appmoviles.retodos.model.playlist.Data track = deezer.getTracks().getData()[positionClicked];
        i.putExtra("cover_big",deezer.getCover_big());
        i.putExtra("title",track.getTitle());
        i.putExtra("artist",track.getArtist().getName());
        i.putExtra("album",listNameTV.getText().toString());
        i.putExtra("duration",convertTime(track.getDuration()));
        i.putExtra("preview",track.getPreview());
        this.startActivity(i);
        this.finish();
    }

}
