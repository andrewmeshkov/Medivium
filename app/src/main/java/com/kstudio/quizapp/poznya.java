package com.kstudio.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class poznya extends AppCompatActivity {
TextView a;
float x1, x2 , y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poznya);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");

        a = (TextView) findViewById(R.id.rum);

        a.setTypeface(typeface);

    }
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                Intent i = new Intent(poznya.this, glavniesob.class);
                startActivity(i);
            }else if(x1 > x2){
                Intent i = new Intent(poznya.this, pereselenie.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}