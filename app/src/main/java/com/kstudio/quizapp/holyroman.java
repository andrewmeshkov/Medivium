package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class holyroman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holyroman);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        TextView a = (TextView) findViewById(R.id.holyone);

        a.setTypeface(typeface);
        TextView ad = (TextView) findViewById(R.id.trree);

        ad.setTypeface(typeface);
        TextView ae = (TextView) findViewById(R.id.two);

        ae.setTypeface(typeface);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("СВЯЩЕННАЯ РИМСКАЯ ИМПЕРИЯ");

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
