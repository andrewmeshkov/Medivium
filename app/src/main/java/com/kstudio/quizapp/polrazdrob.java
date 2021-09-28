package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class polrazdrob extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polrazdrob);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");


    }
}
