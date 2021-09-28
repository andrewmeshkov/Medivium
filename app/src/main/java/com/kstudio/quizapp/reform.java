package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class reform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reform);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        TextView a = (TextView) findViewById(R.id.reforw);

        a.setTypeface(typeface);

        TextView am = (TextView) findViewById(R.id.tyuiop);

        am.setTypeface(typeface);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("РЕФОРМАЦИЯ");


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
