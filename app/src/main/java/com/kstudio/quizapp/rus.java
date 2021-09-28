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

public class rus extends Fragment {

    private AdView mAdView;
    GridView androidGridView;
    public static final String CAT = "com.aim.sliitquizapp.SUBJECT";
    String category="";

    String[] gridViewString = {"ОБЩИЙ ТЕСТ РУСЬ", "ДРЕВНИЕ ЛЮДИ",
            "ПЕРВЫЕ ГОСУДАРСТВА", "ВОСТОЧНЫЕ СЛАВЯНЕ",
            "ВЛАДИМИР I", "ЯРОСЛАВ МУДРЫЙ ","ИВАН КАЛИТА","ДМИТРИЙ ДОНСКОЙ",
            "ВЛАДИМИР МОНОМАХ", "ПОЛИТИЧЕСКАЯ РАЗДРОБЛЕННСТЬ",
            "МОНГОЛЬСКОЕ НАШЕСТВИЕ", "СЕВЕРО-ВОСТОЧНАЯ РУСЬ",
            "ОБЪЕДИНЕНИЕ РУСИ", "ОСВОБОЖДЕНИЕ ОТ ИГА",
            "РУССКАЯ ЦЕРКОВЬ ", "РУССКИЙ ГОРОД ",
            "РОССИЙСКОЕ ГОСУДАРСТВО", "РУССКАЯ КУЛЬТУРА"} ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("РУСЬ");

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
        actionBar.setTitle("РУСЬ");
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
