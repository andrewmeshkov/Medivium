package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class kulrus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kulrus);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        TextView a = (TextView) findViewById(R.id.kulrusodin);
        a.setTypeface(typeface);
        TextView aw = (TextView) findViewById(R.id.kulrusdvawer);
        aw.setTypeface(typeface);
        TextView aas = (TextView) findViewById(R.id.christkulrus);
        aas.setTypeface(typeface);
        TextView aasw = (TextView) findViewById(R.id.pismenrus);
        aasw.setTypeface(typeface);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("КУЛЬТУРА ДРЕВНЕЙ РУСИ");
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
