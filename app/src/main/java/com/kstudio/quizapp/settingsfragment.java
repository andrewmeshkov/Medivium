package com.kstudio.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

import es.dmoral.toasty.Toasty;


public class settingsfragment extends Fragment {
    private Context mContext;
    private Dialog mCustomDialog;

    private AdView mAdView;
    String list[] = new String[]{"ОЧИСТИТЬ СТАТИСТИКУ","УДАЛИТЬ АККАУНТ","ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ","УСЛОВИЯ ПОЛЬЗОВАНИЯ"
    ,"СВЯЗАТЬСЯ С РАЗРАБОТЧИКОМ","ОЦЕНИТЕ ПРИЛОЖЕНИЕ","ОБНОВЛЕНИЕ ПРОФИЛЯ","СМЕНИТЬ ПАРОЛЬ"};
    private FirebaseUser firebaseUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.menu_settings);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mAdView = view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
mContext = Objects.requireNonNull(getActivity()).getApplicationContext();





        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.item_settings,list);
        listView.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.progress_dialog);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference dbNode = FirebaseDatabase.getInstance().getReference().getRoot().child("Statistics").child(firebaseUser.getUid());
                    dbNode.setValue(null);
                    Toast.makeText(getContext(), "Статистика успешно удалена", Toast.LENGTH_SHORT).show();
                }else if (position == 1){
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    final EditText edittext = new EditText(getContext());
                    edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    alert.setMessage("Введите пароль для удаления аккаунта");
                    alert.setTitle("Введите пароль");

                    alert.setView(edittext);

                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            setDialog(true);
                            String pwd = edittext.getText().toString();
                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference dbNode = FirebaseDatabase.getInstance().getReference().getRoot().child("Statistics").child(firebaseUser.getUid());
                            dbNode.setValue(null);
                            AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), pwd);
                            firebaseUser.reauthenticate(credential)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        setDialog(false);
                                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                                    } else {
                                                        setDialog(false);
                                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                                    }
                                                }
                                            });
                                        }
                                    });
                        }
                    });

                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                    alert.show();

                }else if(position == 2){
                    String largeTextString = getStringFromRawRes(R.raw.privacypolicy);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setMessage(largeTextString);
                    dialog.setTitle("Политика конфиденциальности");
                    dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }else if(position == 3){
                    String largeTextString = getStringFromRawRes(R.raw.termsconditions);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                    dialog.setMessage(largeTextString);
                    dialog.setTitle("Условия пользования");
                    dialog.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                else if (position == 4){
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sdeburt@yandex.ru"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Отзыв , вопрос о приложении MEDIVIUM");
                    intent.putExtra(Intent.EXTRA_TEXT, "Введите свое сообщение здесь!");
                    try {
                        startActivity(Intent.createChooser(intent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toasty.error(mContext, "У аккаунта отсутствует почта.", Toasty.LENGTH_SHORT).show();
                    }
                }
                else if(position == 5){
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=com.kstudio.quizapp")));
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=com.kstudio.quizapp" )));
                    }
                }
                else if(position == 6){
                    Intent intent = new Intent(mContext, updateprofilea.class);
                    startActivity(intent);
                }
                else if(position == 7){
                    Intent intent = new Intent(mContext, resetpass.class);
                    startActivity(intent);
                }
            }
        });



        return view;
    }






    private void setDialog(boolean show){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(R.layout.progress_dialog);
        Dialog dialog = builder.create();
        if (show)
            dialog.show();
        else
            dialog.dismiss();
    }

    @Nullable
    private String getStringFromRawRes(int rawRes) {

        InputStream inputStream;
        try {
            inputStream = getResources().openRawResource(rawRes);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String resultString;
        try {
            resultString = byteArrayOutputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        return resultString;
    }























    public void onBackPressed() {
        // TODO Auto-generated method stub
        getActivity().overridePendingTransition(R.anim.close_next, R.anim.open_next);
       getActivity().onBackPressed();
    }




}

