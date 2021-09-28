package com.kstudio.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class updateprofilea extends AppCompatActivity {

    private Spinner sem;
    private  ArrayAdapter<String> SemAdapter;
    private Button update;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private EditText name;
    private Toolbar toolbar;
    private ImageView imageView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profilea);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth= FirebaseAuth.getInstance();
        update=findViewById(R.id.update_button);

        name=findViewById(R.id.Name_details);
        sem=findViewById(R.id.Sem_spinner);
        imageView = findViewById(R.id.userImage);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        SemAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,this.getResources().getStringArray(R.array.Sem_Choice));
        SemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sem.setAdapter(SemAdapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                usera user=new usera(name.getText().toString(),sem.getSelectedItem().toString());
                if(name!=null) {
                    //
                    mDatabase.child("users").child(Objects.requireNonNull(auth.getUid()))
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(updateprofilea.this,"ОБНОВЛЕНО",Toast.LENGTH_SHORT).show();
                            refresh();//Refresh 9
                            finish();
                            progressDialog.dismiss();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    });
                }else Toast.makeText(updateprofilea.this,"ВВЕДИТЕ ИМЯ",Toast.LENGTH_SHORT).show();
            }
        });
        mDatabase.child("users").child(Objects.requireNonNull(auth.getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(name.getText().toString().equals("")&&dataSnapshot.exists()){
                    usera user=dataSnapshot.getValue(usera.class);
                    assert user != null;
                    name.setText(user.name);
                    sem.setSelection(Arrays.asList(updateprofilea.this.getResources()
                            .getStringArray(R.array.Sem_Choice)).indexOf(user.semester));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    public void refresh() {

        Intent intent = new Intent(updateprofilea.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                String userUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                UploadTask uploadTask = FirebaseStorage.getInstance()
                        .getReference().child(userUid).putFile(mImageUri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageUtis.displayRoundImageFromUrl(updateprofilea.this, mImageUri.toString(), imageView);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}