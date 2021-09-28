package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class obuchai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obuchai);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("РУССКИЕ ОБЫЧАИ");
        TextView a = (TextView) findViewById(R.id.obcwer);
        a.setTypeface(typeface);



    }@Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
