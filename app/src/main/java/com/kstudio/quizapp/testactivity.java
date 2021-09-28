package com.kstudio.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kstudio.quizapp.model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.kstudio.quizapp.europetest.CAT;

public class testactivity extends AppCompatActivity {

    Button btnNxt;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4,rb;
    TextView tvTimer,tvQno,tvQus;
    DatabaseReference reference;
    DatabaseReference reference1;
    private int correct=0,wrong=0,total=1,noOfQus=1,qNo;
    ArrayList<Question> list = new ArrayList<>();
    CountDownTimer timer;
    Question question;
    String category;
    public static final int time = 1200;
    boolean doubleBackToExitPressedOnce = false;
    ImageView
            question_image;
    public static final String CORRECT = "com.kstudio.quizapp.CORRECT";
    public static final String WRONG = "com.kstudio.quizapp.WRONG";
    public static final String TOTAL = "com.kstudio.quizapp.TOTAL";
    public static final String CATEGORY = "com.kstudio.quizapp.CATEGORY";
    public static final String QLIST = "com.kstudio.quizapp.QLIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnNxt = findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);
        tvTimer = findViewById(R.id.tvTimer);
        tvQus = findViewById(R.id.tvQuestion);
        tvQno = findViewById(R.id.tvQCount);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        question_image = (ImageView)findViewById(R.id.question_image);

        Intent intent = getIntent();
        category = intent.getStringExtra(CAT);

        this.setTitle(category);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress_dialog);
        final Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        reference1 = FirebaseDatabase.getInstance().getReference().child(category);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noOfQus = (int) dataSnapshot.getChildrenCount();
                qNo = noOfQus;
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        next();

    }

    public void next(){
        if(total>noOfQus){
            Intent intent = new Intent(testactivity.this,ResultActivity.class);
            intent.putExtra(CORRECT,String.valueOf(correct));
            intent.putExtra(WRONG,String.valueOf(wrong));
            intent.putExtra(TOTAL,String.valueOf(noOfQus));
            intent.putExtra(CATEGORY,category);

            intent.putParcelableArrayListExtra(QLIST,(ArrayList<Question>) list);
            timer.cancel();
            startActivity(intent);
        }else {
            reference = FirebaseDatabase.getInstance().getReference().child(category).child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    question = dataSnapshot.getValue(Question.class);


                    assert question != null;
                    if(question.getIsImageQuestion().equals("true")){
    Picasso.with(getBaseContext()).load(question.getImage()).into(question_image);
    question_image.setVisibility(View.VISIBLE);
                        tvQus.setText(" " + (total - 1) + " . " + question.getQuestion());
}else
{
    tvQus.setText(" " + (total - 1) + " . " + question.getQuestion());
    question_image.setVisibility(View.INVISIBLE);
    tvQus.setVisibility(View.VISIBLE);
}
                    rb1.setText(question.getOption1());
                    rb2.setText(question.getOption2());
                    rb3.setText(question.getOption3());
                    rb4.setText(question.getOption4());
                    tvQno.setText(String.valueOf(total-1));

                    reverseTimer(time,tvTimer);
                    tvQno.setText("ВОПРОС : "+(total - 1));

                    btnNxt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final int s = radioGroup.getCheckedRadioButtonId();
                            rb = findViewById(s);
                            timer.cancel();
                            String choice = null;
                            String st = null;
                            if (s == -1) {
                                choice = "-";
                                st = "s";
                            } else if (rb.getText().equals(question.getAnswer())) {
                                choice = rb.getText().toString();
                                st = "c";
                                correct++;
                            } else {
                                wrong++;
                                st = "w";
                                choice = rb.getText().toString();
                            }
                            question.setChoice(choice);
                            question.setSt(st);
                            list.add(question);
                            next();
                            radioGroup.clearCheck();
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(testactivity.this, "ВОПРОС ОТСУТСТВУЕТ", Toast.LENGTH_SHORT).show();
                }

            });
        }
        total++;

        if (total-1 == qNo)
            btnNxt.setText("КОНЕЦ");
    }

    public void reverseTimer(int seconds,final TextView tv){
         timer = new CountDownTimer(seconds*1000,1000){
            public void onTick(long millisUntilFinished){
                int seconds = (int)(millisUntilFinished/1000);
                int minutes = seconds/60;
                seconds = seconds % 60;

                if(seconds <= 5)
                    tv.setTextColor(getResources().getColor(R.color.red));
                else
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));

                if(doubleBackToExitPressedOnce)
                    cancel();
            }
            public void onFinish(){
                tv.setText("00:00");
                final int s = radioGroup.getCheckedRadioButtonId();
                rb = findViewById(s);
                String st;
                String choice = null;
                if (s == -1) {
                    choice = "-";
                    st = "s";
                } else if (rb.getText().equals(question.getAnswer())) {
                    choice = rb.getText().toString();
                    st = "c";
                    correct++;
                } else {
                    wrong++;
                    st = "w";
                    choice = rb.getText().toString();
                }
                question.setChoice(choice);
                question.setSt(st);
                list.add(question);
                radioGroup.clearCheck();
                next();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            timer.cancel();
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "НАЖМИТЕ ЕЩЕ РАЗ ДЛЯ ВЫХОДА ИЗ ТЕСТА", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
