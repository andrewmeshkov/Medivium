package com.kstudio.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kstudio.quizapp.model.Question;
import com.kstudio.quizapp.model.Statistics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();
    TextView tvP,tvCorrect,tvWrong,tvSkip,tvComment;
    public static final String QLIST = "com.kstudio.quizapp.QLIST";
    ArrayList<Question> list = new ArrayList<Question>();
    int flag = 0;
    DatabaseReference reference;
    Statistics stat;
    private FirebaseUser firebaseUser;
    private InterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        tvCorrect = findViewById(R.id.tvCorrect);
        tvWrong = findViewById(R.id.tvWrong);
        tvSkip = findViewById(R.id.tvSkip);
        tvP = findViewById(R.id.tvPercentage);
        tvComment = findViewById(R.id.tvComment);

        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra(testactivity.QLIST);
        String cat = intent.getStringExtra(testactivity.CATEGORY);
        String cr = intent.getStringExtra(testactivity.CORRECT);
        String wr = intent.getStringExtra(testactivity.WRONG);
        String tot = intent.getStringExtra(testactivity.TOTAL);

        int correct = Integer.parseInt(cr);
        int wrong = Integer.parseInt(wr);
        int total = Integer.parseInt(tot);
        int skip = total-correct-wrong;

        final int p = (correct*100)/total;

        tvCorrect.setText(cr);
        tvWrong.setText(wr);
        tvSkip.setText(String.valueOf(skip));

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        final ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);
        mProgress.setSecondaryProgress(100);
        mProgress.setMax(100);
        mProgress.setProgressDrawable(drawable);

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus<p) {
                    pStatus += 1;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tvP.setText(pStatus + "%");
                        }
                    });
                    try {
                        Thread.sleep(8);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        if(p >= 80) {
            tvComment.setText(R.string.tx_best);
        }else if(p >= 50) {
            tvComment.setText(R.string.tx_good);
        }else{
            tvComment.setText(R.string.tx_bad);
        }


        if(flag == 0) {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference().child("Statistics").child(firebaseUser.getUid());
            stat = new Statistics();
            stat.setCorrect(correct);
            stat.setWrong(wrong);
            stat.setScore(p);
            stat.setSkip(skip);
            stat.setCategory(cat);

            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            stat.setDate(date);

            reference.push().setValue(stat);

            flag++;
        }

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdLeftApplication() {
            }


        });



    }

    public void retakeBtn(View view){

        if (rewardedAd.isLoaded()) {
            Activity activityContext = this;
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {

                }

                @Override
                public void onRewardedAdClosed() {
                    startActivity(new Intent(ResultActivity.this,MainActivity.class));
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {

                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    startActivity(new Intent(ResultActivity.this,MainActivity.class));
                }
            };
            rewardedAd.show(activityContext, adCallback);
        } else {
            Log.d("TAG", "The rewarded ad wasn't loaded yet.");
        }


    }

    @Override
    public void onBackPressed() {

    }
}
