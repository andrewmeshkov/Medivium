package com.kstudio.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.kstudio.quizapp.adapters.CustomGridView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ogest extends Fragment {

    private AdView mAdView;
    GridView androidGridView;
    public static final String CAT = "com.kstudio.sliitquizapp.SUBJECT";
    String category="";

    String[] gridViewString = {
            "ОГЭ-ВАРИАНТ 1","ОГЭ-ВАРИАНТ 2","ОГЭ-ВАРИАНТ 3",
            "ОГЭ-ВАРИАНТ 4","ОГЭ-ВАРИАНТ 5","ОГЭ-ВАРИАНТ 6",
            "ОГЭ-ВАРИАНТ 7","ОГЭ-ВАРИАНТ 8","ОГЭ-ВАРИАНТ 9",
            "ОГЭ-ВАРИАНТ 10","ОГЭ-ВАРИАНТ 11","ОГЭ-ВАРИАНТ 12",
            "ОГЭ-ВАРИАНТ 13","ОГЭ-ВАРИАНТ 14","ОГЭ-ВАРИАНТ 15"} ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("ОГЭ");

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("ОГЭ");
        final CustomGridView adapterViewAndroid = new CustomGridView(getContext(), gridViewString);
        androidGridView = view.findViewById(R.id.qwertgfdsa);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                category = gridViewString[+i];
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

