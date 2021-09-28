package com.kstudio.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.Nullable;

public class splash extends AppCompatActivity {

    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        avLoadingIndicatorView = findViewById(R.id.avi);
        avLoadingIndicatorView.smoothToShow();
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        anim.reset();
        ConstraintLayout l = findViewById(R.id.lin_lay);
        l.clearAnimation();
        anim=AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();



        int SPLASH_DISPLAY_LENGTH = 2090;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this, LoginActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                splash.this.startActivity(mainIntent);
                avLoadingIndicatorView.smoothToHide();
                splash.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
