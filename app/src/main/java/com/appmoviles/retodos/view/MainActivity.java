package com.appmoviles.retodos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appmoviles.retodos.R;
import com.appmoviles.retodos.model.search.Data;
import com.appmoviles.retodos.model.search.Deezer;
import com.appmoviles.retodos.model.SearchItem;
import com.appmoviles.retodos.model.SearchItemAdapter;
import com.appmoviles.retodos.util.Album;
import com.appmoviles.retodos.util.Constants;
import com.appmoviles.retodos.util.HTTPSWebUtilDomi;
import com.appmoviles.retodos.util.Model;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements HTTPSWebUtilDomi.OnResponseListener {

    private RecyclerView recyclerView;
    private SearchItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int positionClicked;
    private Deezer deezerSearch;
    private ArrayList<SearchItem> items;
    private EditText searchBarET;
    private ImageView searchImgIV;
    private HTTPSWebUtilDomi utilDomi;
    private Model modelUtil;

    private String searchBaseUrl = "https://api.deezer.com/search?q=album:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<SearchItem>();
        modelUtil = new Model(deezerSearch);
        buildRecycleView();

        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        searchImgIV = findViewById(R.id.searchImgIV);
        searchImgIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    listSearch();
                }
            }
        });
        searchBarET = findViewById(R.id.searchBarET);

    }

    @Override
    public void onResponse(int callbackID, String response) {
        deezerSearch = null;
        switch(callbackID) {
            case Constants.SEARCH_CALLBACK:
                Gson gson =  new Gson();
                deezerSearch = gson.fromJson(response, Deezer.class);
                break;
        }
    }

    private void listSearch() {
        String text = searchBarET.getText().toString().trim();
        if (text.isEmpty() || text.length()<3)
            return;
        Thread t = new Thread(
                () -> {
                    utilDomi.GETrequest(Constants.SEARCH_CALLBACK,searchBaseUrl+"\""+text+"\"&limit=3");
                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        adapter.delete();
        modelUtil.setDeezer(deezerSearch);
        HashMap<String, Album> abs = modelUtil.getAlbums();
        for(String key: abs.keySet()) {
            Album a = modelUtil.getAlbums().get(key);
            items.add(new SearchItem(a.getCover(),a.getName(),a.getUserCreator(),a.getnItems()+""));
        }
        adapter.notifyDataSetChanged();
    }

    private void buildRecycleView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SearchItemAdapter(items);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SearchItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                positionClicked = position;
                openListActivity();
            }
        });
    }

    private String getUrl() {
        com.appmoviles.retodos.util.Album a = modelUtil.getAlbum(items.get(positionClicked).getName());
        return a.getTrackList();
    }

    private void openListActivity() {
        Intent i = new Intent(this,ListActivity.class);
        i.putExtra("url", getUrl());
        this.startActivity(i);
        this.finish();
    }

    public Model getModelUtil() {
        return modelUtil;
    }
}
