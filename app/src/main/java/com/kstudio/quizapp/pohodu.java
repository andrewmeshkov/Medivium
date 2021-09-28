package com.kstudio.quizapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class pohodu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pohodu);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        TextView a = (TextView) findViewById(R.id.kresto);

        a.setTypeface(typeface);TextView ax = (TextView) findViewById(R.id.krestdav);

        ax.setTypeface(typeface);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("КРЕСТОВЫЕ ПОХОДЫ");
        TextView axc = (TextView) findViewById(R.id.kresttri);

        axc.setTypeface(typeface);
        TextView axcd = (TextView) findViewById(R.id.krestche);

        axcd.setTypeface(typeface);


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
