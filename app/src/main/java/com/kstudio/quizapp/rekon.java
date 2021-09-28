package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class rekon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekon);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("РЕКОНКИСТА");
        TextView a = (TextView) findViewById(R.id.swert);

        a.setTypeface(font);



    }@Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
