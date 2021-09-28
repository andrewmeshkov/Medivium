package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class muzuka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzuka);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        TextView a = (TextView) findViewById(R.id.myzik);
        a.setTypeface(typeface);
        TextView aop = (TextView) findViewById(R.id.prin);
        aop.setTypeface(typeface);
        Button mnadonhj=(Button) findViewById(R.id.start);
        mnadonhj.setText("СТАРТ");
        mnadonhj.setTypeface(typeface);
        Button mnadonhjw=(Button) findViewById(R.id.stop);
        mnadonhjw.setText("СТОП");
        mnadonhjw.setTypeface(typeface);
        final MediaPlayer mediaPlayer = MediaPlayer.create(muzuka.this, R.raw.russong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("НАРОДНАЯ МУЗЫКА");
        mnadonhj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();


            }
        });


        mnadonhjw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });



    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}