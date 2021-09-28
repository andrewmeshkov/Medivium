package com.kstudio.quizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PassTest extends AppCompatActivity {
    ArrayList<Quezcus> questions;
    String[]answers;
    Toolbar toolbar;
    DiscreteScrollView scrollView;
    LinearLayout indexLayout;
    GridView quesGrid;
    ArrayList<String> list;
    ArrayList<String> arrayList;
    int flag_controller = 1;
    long timer =60*1000;
    popGridAdapter popGrid;
    Button next,prev;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private String TESTNAME;
    private RadioGroup group;
    private int countPaused = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_pass);
        questions=((Test) getIntent().getExtras().get("Questions")).getQuestions();
        TESTNAME = (String) getIntent().getExtras().get("TESTNAME");
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        answers=new String[questions.size()];
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        scrollView = findViewById(R.id.discrete);
        final QuestionAdapter questionAdapter=new QuestionAdapter(questions);
        scrollView.setAdapter(questionAdapter);

        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scrollView.getCurrentItem()==questions.size()-1){
                   showPopUp();
                }else {
                    //setNextPrevButton(scrollView.getCurrentItem() + 1);
                    scrollView.smoothScrollToPosition(scrollView.getCurrentItem() + 1);
                }
            }
        });

        prev=findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(scrollView.getCurrentItem()!=0){
                    //setNextPrevButton(scrollView.getCurrentItem()-1);
                    scrollView.smoothScrollToPosition(scrollView.getCurrentItem()-1);
                }
            }
        });

        setNextPrevButton(scrollView.getCurrentItem());
        indexLayout=findViewById(R.id.index_layout);
        indexLayout.setAlpha(.5f);
        quesGrid=findViewById(R.id.pop_grid);
        popGrid=new popGridAdapter(PassTest.this);
        quesGrid.setAdapter(popGrid);
        quesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                scrollView.smoothScrollToPosition(i+1);
                slideUp(indexLayout);
            }
        });
        scrollView.addScrollListener(new DiscreteScrollView.ScrollListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {
                setNextPrevButton(newPosition);
            }
        });

        timer=60*1000;
    }
    void showPopUp(){
        AlertDialog.Builder builder=new AlertDialog.Builder(PassTest.this);
        builder.setMessage("ВЫ ХОТИТЕ ЗАКОНЧИТЬ?");
        builder.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                submit();
//                setAlertDialog(answerText);
                dialogStart();
            }
        });

        builder.setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    /*submit result to database**/
    void submit(){
        flag_controller = 0;
        int score=0;
        list = new ArrayList<>();
        arrayList = new ArrayList<>();
        for(int i=0;i<answers.length;i++){
            if(answers[i]!=null&&answers[i].equals(questions.get(i).getAnswer())){
                score++;
            }
            String temp = (answers[i]!=null) ? answers[i]+") ":"null) ";

            list.add("ВАШ ВЫБОР ("+
                    temp +
                    "ПРАВИЛНЫЙ ОТВЕТ("+ questions.get(i).getAnswer()+")");
            arrayList.add(questions.get(i).getQuestion());
        }

        try {
            mDatabase.child("Results").child(((Test) getIntent().getExtras().get("Questions")).getName())
                    .child(auth.getUid()).setValue(score);
        }catch (Exception e){
            Log.e("Result Update Failed " ,e.getMessage());
        }
    }

    void dialogStart() {

        final AlertDialog.Builder builderSingle = new AlertDialog.Builder(PassTest.this);
        builderSingle.setIcon(R.mipmap.ic_launcher_round);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (PassTest.this, android.R.layout.select_dialog_singlechoice);
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>
                (PassTest.this,android.R.layout.select_dialog_singlechoice);

        for(String y : arrayList) {
            arrayAdapter1.add(y);
        }
        for(String x: list){
            arrayAdapter.add(x);
        }

        builderSingle.setCancelable(false);
        builderSingle.setNegativeButton("ГОТОВО!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(PassTest.this);
                builderInner.setMessage(strName);
                builderInner.setCancelable(false);
                builderInner.setTitle("ВАШ ОТВЕТ");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                        builderSingle.show();
//                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(countPaused<=2 && countPaused >=0 && flag_controller == 1)
            startService(new Intent(PassTest.this,
                    notification.class));
        countPaused++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(PassTest.this, notification.class));
        if(countPaused>2) {

            countPaused = -1000;
            submit();
            dialogStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        stopService(new Intent(PassTest.this, notification.class));
}

    void setNextPrevButton(int pos){
        if(pos==0){
            prev.setText("");
        }else {
            prev.setText("НАЗАД");
        }
        if(pos==questions.size()-1){
            next.setText("ОТПРАВИТЬ");
        }else {
            next.setText("ДАЛЕЕ");
        }
    }

    @Override
    public void onBackPressed() {
        showPopUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.attempt_menu, menu);
        final MenuItem counter = menu.findItem(R.id.counter);

        new CountDownTimer(timer, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                long hr= TimeUnit.MILLISECONDS.toHours(millis),mn=(TimeUnit.MILLISECONDS.toMinutes(millis)-
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                        sc= TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));


                String hms =format(hr)+":"+format(mn)+":"+format(sc) ;
                counter.setTitle(hms);
                timer = millis;
            }
            String format(long n){
                if(n<10)
                    return "0"+n;
                else return ""+n;
            }

            public void onFinish() {
                submit();
                dialogStart();
            }
        }.start();

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.submit){
            showPopUp();

            return true;
        }else if(id==R.id.info){
            togglePopUp();
        }
        return super.onOptionsItemSelected(item);
    }


    void togglePopUp(){
        if(indexLayout.getVisibility()== View.GONE){
            slideDown(indexLayout);
        }else slideUp(indexLayout);
    }

    class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

        private int itemHeight;
        private ArrayList<Quezcus> data;

        QuestionAdapter(ArrayList<Quezcus> data) {
            this.data = data;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            Activity context = (Activity) recyclerView.getContext();
            Point windowDimensions = new Point();
            context.getWindowManager().getDefaultDisplay().getSize(windowDimensions);
                itemHeight = Math.round(windowDimensions.y * 0.8f);
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.frag_test, parent, false);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight);
            v.setLayoutParams(params);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.questionText.setText(data.get(position).getQuestion());
            if(data.get(position).getIsImageQuestion().equals("true")){
                Picasso.with(getBaseContext()).load(data.get(position).getImage()).into(holder.questionTexat);
                holder.questionText.setVisibility(View.VISIBLE);
            }else
            {
                holder.questionTexat.setVisibility(View.INVISIBLE);
                holder.questionText.setVisibility(View.VISIBLE);
            }
            holder.r1.setText(data.get(position).getOpt_A());
            holder.r2.setText(data.get(position).getOpt_B());
            holder.r3.setText(data.get(position).getOpt_C());
            holder.r4.setText(data.get(position).getOpt_D());
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    final int selectedId = holder.radioGroup.getCheckedRadioButtonId();
                    if(i==R.id.radioButton){
                        answers[position]="A";
                    }  else if(i==R.id.radioButton2){
                        answers[position]="B";
                    }else if(i==R.id.radioButton3){
                        answers[position]="C";
                    }else if(i==R.id.radioButton4){
                        answers[position]="D";
                    }

                    popGrid.notifyDataSetChanged();
                }
            });


            if(answers[position]==null) {
                holder.radioGroup.clearCheck();
            }else if(answers[position].equals("A")) {
                holder.radioGroup.check(R.id.radioButton);
            }else if(answers[position].equals("B")) {
                holder.radioGroup.check(R.id.radioButton2);
            }else if(answers[position].equals("C")) {
                holder.radioGroup.check(R.id.radioButton3);
            }else if(answers[position].equals("D")) {
                holder.radioGroup.check(R.id.radioButton4);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private View overlay;
            private TextView questionText;
            private RadioGroup radioGroup;
            private ImageView questionTexat;
            private RadioButton r1,r2,r3,r4,r5;

            ViewHolder(View itemView) {
                super(itemView);
                questionText =  itemView.findViewById(R.id.questionTextView);
                radioGroup=itemView.findViewById(R.id.radioGroup);
                r1=itemView.findViewById(R.id.radioButton);
                r2=itemView.findViewById(R.id.radioButton2);
                r3=itemView.findViewById(R.id.radioButton3);
                questionTexat=itemView.findViewById(R.id.plmoknijb);
                r4=itemView.findViewById(R.id.radioButton4);
            }

            public void setOverlayColor(@ColorInt int color) {
                overlay.setBackgroundColor(color);
            }

            public void unCheck() {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton) {
                    r1.setChecked(true);
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton2) {
                    r2.setChecked(true);
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                    r3.setChecked(true);
                }
                else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton4) {
                    r4.setChecked(true);
                }

            }
        }
    }

    class popGridAdapter extends BaseAdapter {
        Context mContext;
        popGridAdapter(Context context){
            mContext=context;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View convertView;
            if(view==null){
                convertView=new Button(mContext);
            }else convertView=view;
            if(answers[i]==null)
                (convertView).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            else
                (convertView).setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

            ((Button)convertView).setText(""+(i+1));

            (convertView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    scrollView.smoothScrollToPosition(i);
                }
            });
            return convertView;
        }
    }

    public void slideUp(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                0,
               -view.getHeight());
        animate.setDuration(500);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public void slideDown(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                -view.getHeight(),
                0);
        animate.setDuration(500);
        view.startAnimation(animate);

    }

    @Override
    protected void onDestroy() {
        submit();
        super.onDestroy();
    }
}

