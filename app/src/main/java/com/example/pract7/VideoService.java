package com.example.pract7;

import android.content.Context;
import android.net.Uri;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoService {
    public static void playRawVideo(Context context, VideoView vw, String resName){
        try {
            String pkgName = context.getPackageName();
            int id = context.getResources().getIdentifier(resName, "raw", pkgName);
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" +id);
            vw.setVideoURI(uri);
        }
        catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void playLocalVideo(Context context, VideoView vw, String resName){
        try {
            Uri uri = Uri.parse(resName);
            vw.setVideoURI(uri);
        }
        catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void playURLVideo(Context context, VideoView vw, String resName){
        try {
            vw.setVideoPath(resName);
        }
        catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
