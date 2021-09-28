package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class harakchertu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harakchertu);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");


        TextView a = (TextView) findViewById(R.id.harak);

        a.setTypeface(typeface);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ХАРАКТЕРНЫЕ ЧЕРТЫ");

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
