package com.kstudio.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kstudio.quizapp.adapters.CustomGridView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class europetest extends Fragment {

    private AdView mAdView;
    GridView GridView;
    public static final String CAT = "com.kstudio.sliitquizapp.SUBJECT";
    String category="";

    String[] gridString = {
            "ОБЩИЙ ТЕСТ ЕВРОПА",
            "ПАДЕНИЕ ЗАПАДНОЙ РИМСКОЙ ИМПЕРИЕЙ", "ВЕЛИКОЕ ПЕРЕСЕЛЕНИЕ НАРОДОВ",
            "РАННЕЕ СРЕДНЕВЕКОВЬЕ", "ФРАНКСКОЕ ГОСУДАРТВО",
            "ВИЗАНТИЙСКАЯ ИМПЕРИЯ", "РАСПРОСТРАНЕНИЕ ХРИСТИАНСТВА",
            "АРАБО-МУСУЛЬМАНСКИЙ МИР", "КРЕСТОВЫЕ ПОХОДЫ",
            "СВЯЩЕННАЯ РИМСКАЯ ИМПЕРИЯ", "ЦЕРКОВЬ В СРЕДНЕВЕКОВЬЕ",
            "НАУКА , СХОЛАСТИКА И УНИВЕРСИТЕТЫ", "ПЕРИОД ВЫСОКОГО СРЕДНЕВЕКОВЬЯ",
            "ПОЗДНЕЕ СРЕДНЕВЕКОВЬЕ", "СТОЛЕТНЯЯ ВОЙНА",
            "НАЧАЛО РЕННЕСАНСА","РЕФОРМАЦИЯ В ЕВРОПЕ","ИСКУССТВО",
            "АРХИТЕКТУРА"} ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("ЕВРОПА");

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("ЕВРОПА");
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("ЕВРОПА");
        final CustomGridView adapterViewAndroid = new CustomGridView(getContext(), gridString);
        GridView = view.findViewById(R.id.qwertgfdsa);
        GridView.setAdapter(adapterViewAndroid);
        GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                category = gridString[+i];
                Intent intent = new Intent(getContext(), testactivity.class);
                intent.putExtra(CAT,category);
                startActivity(intent);
            }
        });

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;

    }



}

