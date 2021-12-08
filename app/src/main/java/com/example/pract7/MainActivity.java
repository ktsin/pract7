package com.example.pract7;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    static final String RAW_VIDEO = "bobr.mp4";
    static final String LOCAL_VIDEO = "";
    static final String URL_VIDEO = "http://techslides.com/demos/sample-videos/small.mp4";
    VideoView player;
    private ActivityResultLauncher<String[]> openDocument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button raw = (Button) findViewById(R.id.raw_button);
        Button local = (Button) findViewById(R.id.local_button);
        Button url = (Button) findViewById(R.id.url_button);
        player = findViewById(R.id.player);
        Uri myVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bobr);
        Log.d("D001", myVideoUri.toString());
        player.setVideoURI(myVideoUri);
        MediaController mC = new MediaController(this);
        player.setMediaController(mC);
        mC.setMediaPlayer(player);
        player.setOnPreparedListener((mp)->{
            player.seekTo(0);
            player.start();
            mp.setOnVideoSizeChangedListener((mp_, width, height)->{
                mC.setAnchorView(player);
            });
        });
        raw.setOnClickListener((v)->{
            VideoService.playRawVideo(this, player, RAW_VIDEO);
        });
         local.setOnClickListener((v)->{
             openDocument.launch(new String[]{"*/*"});
            //VideoService.playLocalVideo(this, player, LOCAL_VIDEO);
        });
         url.setOnClickListener((v)->{
            VideoService.playURLVideo(this, player, URL_VIDEO);
        });
        ((Button)findViewById(R.id.play_button)).setOnClickListener((v)->player.start());
        ((Button)findViewById(R.id.stop_button)).setOnClickListener((v)->{player.stopPlayback(); player.resume();});
        ((Button)findViewById(R.id.pause_button)).setOnClickListener((v)->player.pause());
        openDocument = registerForActivityResult(new ActivityResultContracts.OpenDocument(),
                result -> {
                    if (result != null)
                        VideoService.playLocalVideo(this, player, result.toString());

                });

    }
}