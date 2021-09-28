package com.kstudio.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class pois extends Fragment {
    GridView listView;
    ListViewTwo adapter;
    private AdView mAdView;
    ListView androidGridView;
    public static final String CAT = "com.kstudio.sliitquizapp.SUBJECT";
    String category = "";
    private ArrayList<modeltwo> arrayLista = new ArrayList<modeltwo>();

    String[] title = new String[]{"ГЛАВНЫЕ СОБЫТИЯ - ЕВРОПА", "ПОЗДНЯЯ РИМСКАЯ ИМПЕРИЯ", "ВЕЛИКОЕ ПЕРЕСЕЛЕНИЕ НАРОДОВ", "ВАРВАРСКИЕ КОРОЛЕВСТВА",
            "ВИЗАНТИЙСКАЯ ИМПЕРИЯ",
            "РАСПРОСТРНЕНИЕ ХРИСТИАНСТВА",
            "АРАБСКИЙ МИР ",
            "ФРАНКСКОЕ ГОСУДАРСТВО", "РЕКОНКИСТА", "ФЕОДАЛИЗМ",
            "КРЕСТОВЫЕ ПОХОДЫ", "ЦЕРКОВЬ", "СХОЛАСТИКА", "ТОРГОВЛЯ   КОММЕРЦИЯ",
            "СВЯЩЕННАЯ РИМСКАЯ ИМПЕРИЯ",
            "РАЗВИТИЕ НАУКИ И ТЕХНОЛОГИЙ", "СТОЛЕТНЯЯ ВОЙНА",
            "ЗАВОЕВАНИЯ ОСМАНСКОЙ ИМПЕРИИ", "РЕФОРМАЦИЯ", "РЕННЕСАНС",
            "ХАРАКТЕРНЫЕ ЧЕРТЫ", "МЕДЕЦИНА", "ОДЕЖДА", "ЖИВОПИСЬ",
            "МИНИАТЮРА", "СКУЛЬПТУРА", "ГОТИКА", "РОМАНСКИЙ СТИЛЬ"};
    String[] description = new String[]{"glaeur", "poznyy", "peresel",
            "varvar", "vizan", "christ",
            "arab", "frank", "rekon",
            "feod", "pohod", "cze",
            "shol", "torgikom", "hpolyrom",
            "naitech", "stovoyna", "osman", "reform", "renness",
            "harachert", "medi", "odezda", "zhivopis",
            "miniatur", "skulpt", "gotika", "roamans"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.menu_home);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_poiski, container, false);

        listView = view.findViewById(R.id.asdddd);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("ЕВРОПА");
        for (int i = 0; i < title.length; i++) {
            modeltwo modeltwo = new modeltwo(title[i], description[i]);

            arrayLista.add(modeltwo);
        }


        adapter = new ListViewTwo(getContext(), arrayLista);

        listView.setAdapter(adapter);


        return view;

    }



}