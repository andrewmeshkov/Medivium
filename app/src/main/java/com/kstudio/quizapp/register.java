package com.kstudio.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class register extends AppCompatActivity {
    private static final String USERS = "Users";
    private String TAG = "RegisterActivity";

    FirebaseDatabase database;


    private FirebaseAuth auth;
    DatabaseReference mDatabase;

    TextView tvLog;
    Button btnReg;
    EditText etEmail,etPwd;
    FirebaseAuth firebaseAuth;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        etPwd = findViewById(R.id.etPwd);
        etEmail = findViewById(R.id.etEmail);
        btnReg = findViewById(R.id.btn);
        tvLog = findViewById(R.id.tv);

        tvLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        final String[] password = {etPwd.getText().toString()};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.progress_dialog);
        final Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);


        mDatabase = database.getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        avLoadingIndicatorView = findViewById(R.id.loader1);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPwd.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("ВВЕДИТЕ ПОЧТУ");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPwd.setError("ПАРОЛЬ ДОЛЖЕН БЫТЬ БОЛЬШЕ 6 СИМВОЛОВ");
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "ПАРОЛЬ ДОЛЖЕН БЫТЬ БОЛЬШЕ 6 СИМВОЛОВ", Toast.LENGTH_SHORT).show();
                    return;
                }

                avLoadingIndicatorView.setVisibility(View.VISIBLE);
                avLoadingIndicatorView.smoothToShow();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                avLoadingIndicatorView.setVisibility(View.GONE);
                                avLoadingIndicatorView.smoothToHide();
                                if (!task.isSuccessful()) {
                                    Toasty.error(register.this, "ПРОВАЛЕНО" + task.getException(),
                                            Toasty.LENGTH_SHORT).show();
                                } else {
                                    Objects.requireNonNull(auth.getCurrentUser()).sendEmailVerification();
                                    Toasty.success(register.this, "УСПЕШНО", Toasty.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        avLoadingIndicatorView.setVisibility(View.GONE);
        avLoadingIndicatorView.hide();
    }
}

