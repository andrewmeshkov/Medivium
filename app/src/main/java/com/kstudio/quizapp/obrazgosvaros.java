package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class obrazgosvaros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrazgosvaros);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");


        TextView a = (TextView) findViewById(R.id.obrazr1);
        a.setTypeface(typeface);
        TextView aaa = (TextView) findViewById(R.id.obrazr2);
        aaa.setTypeface(typeface);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ОБРАЗОВАНИЕ ГОСУДАРСТВА");

    }@Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
