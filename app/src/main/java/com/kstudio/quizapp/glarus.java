package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class glarus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glarus);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ГЛАВНЫЕ СОБЫТИЯ - РУСЬ");

        TextView a = (TextView) findViewById(R.id.textView2);

        a.setTypeface(typeface);

    }
}
