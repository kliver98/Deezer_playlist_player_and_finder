package com.appmoviles.retodos.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appmoviles.retodos.R;
import com.squareup.picasso.Picasso;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView songIV;
    private TextView nameTV;
    private TextView artistTV;
    private TextView albumTV;
    private EditText durationET;
    private Button listenBTN;
    private ImageView backArrowIV;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_view);

        mediaPlayer = MediaPlayer.create(this, Uri.parse(getIntent().getExtras().getString("preview")));
        songIV = findViewById(R.id.songIV);
        nameTV = findViewById(R.id.nameTV);
        artistTV = findViewById(R.id.artistTV);
        albumTV = findViewById(R.id.albumTV);
        durationET = findViewById(R.id.durationET);
        listenBTN = findViewById(R.id.listenBTN);
        listenBTN.setOnClickListener(this);
        backArrowIV = findViewById(R.id.backArrowIV);
        backArrowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListActivity();
            }
        });

        setData();

    }

    private void setData() {
        String[] data = new String[6];
        data[0] = getIntent().getExtras().getString("cover_big");
        data[1] = getIntent().getExtras().getString("title");
        data[2] = getIntent().getExtras().getString("artist");
        data[3] = getIntent().getExtras().getString("album");
        data[4] = getIntent().getExtras().getString("duration");

        Picasso.get().load(data[0]).into(songIV);
        nameTV.setText(data[1]);
        artistTV.setText(data[2]);
        albumTV.setText(data[3]);
        durationET.setText(data[4]+" minutos");

    }

    private void openListActivity() {
        Intent i = new Intent(this,ListActivity.class);
        this.startActivity(i);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            return;
        }
        mediaPlayer.start();
        new Thread(
                () -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        int total = Integer.parseInt(String.valueOf(mediaPlayer.getMetrics().get("android.media.mediaplayer.durationMs")));
                        while (mediaPlayer.isPlaying()) {
                            String m = mediaPlayer.getMetrics().toString();
                            int act = Integer.parseInt(String.valueOf(mediaPlayer.getMetrics().get("android.media.mediaplayer.playingMs")));
                            durationET.setText(ListActivity.convertTime(((act%total)/1000)+""));
                        }
                    }
                }
        ).start();
    }
}
