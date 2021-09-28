package com.kstudio.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class rusteor extends Fragment {
    GridView listView;
    ListViewTwo adapter;
    public static final String CAT = "com.kstudio.sliitquizapp.SUBJECT";
    String category = "";
    private ArrayList<modeltwo> arrayLista = new ArrayList<modeltwo>();

    String[] title = new String[]{"ГЛАВНЫЕ СОБЫТИЯ - РУСЬ",
            "ДРЕВНИЕ ЛЮДИ", "ВОСТОЧНЫЕ СЛАВЯНЕ",
            "НАРОДЫ I ТЫСЯЧЕЛЕТИЯ НЭ", "РАССЕЛЕНИЕ СЛАВЯН", "ОБРАЗОВАНИЕ ГОСУДАРСТВА",
            "ДРЕВНЕРУССКОЕ ГОСУДАРСТВО", "КАТЕГОРИИ НАСЕЛЕНИЯ .РУССКАЯ ПРАВДА",
            "МЕЖДУНАРОДНЫЕ СВЯЗИ РУСИ", "ВЛАДИМИР  I.КРЕЩЕНИЕ РУСИ","КУЛЬТУРА ДРЕВНЕЙ РУСИ",
            "НОВГОРОД И КИЕВ", "ЯРОСЛАВ МУДРЫЙ", "РУСЬ В ПЕРИОД РАЗДРОБЛЕННОСТИ",
            "ПРИЧИНЫ РАСПАДА РУССКОГО ГОСУДАРСТВА",
            "ВЛАДИМИР  МОНОМАХ",
            "МОНГОЛЬСКОЕ ЗАВОЕВАНИЕ", "БОРЬБА ПРОТИВ АГРЕССИИ В XIII ВЕКЕ", "ИВАН КАЛИТА",
            "ДМИТРИЙ ДОНСКОЙ", "МОСКВА КАК ЦЕНТР  ОБЪЕДИНЕНИЯ ЗЕМЕЛЬ",

            "ОСВОБОЖДЕНИЕ ОТ ИГА . ИВАН 3", "ВОССТАНОВЛЕНИЕ ЭКОНОМИКИ РУССКИХ ЗЕМЕЛЬ", "КОЛОНИЗАЦИЯ СЕВЕРО-ВОСТОКА РУСИ",
            "ФОРМЫ ЗЕВЛЕВЛАДЕНИЯ", "РУССКИЙ ГОРОД",
            "РОЛЬ ЦЕРКВИ В ЖИЗНИ", "КУЛЬТУРНОЕ РАЗВИТИЕ РУССКИХ ЗЕМЕЛЬ", "СТАНОВЛЕНИЕ РОССИЙСКОГО ГОСУДАРСТВА",
            "ОБРАЗОВАНИЕ РОССИЙСКОГО ГОСУДАРСТВА", "БАНИ",
            "РУССКАЯ ПЕЧЬ ", "НАЦИОНАЛЬНОЕ ЖИЛИЩЕ", "НАЦИОНАЛЬНЫЙ КОСТЮМ",
            "РУССКАЯ КУХНЯ", "НАРОДНАЯ МУЗЫКА",
            "НАРОДНОЕ ТВОРЧЕСТВО", "РУССКИЕ ОБЫЧАИ", "НАРОДНЫЕ СКАЗКИ ",
            "НАРОДНЫЕ ТАНЦЫ", "РУССКИЙ ФОЛЬКЛОР"};
    String[]  description = new String[]{"glarus", "drevn","vostsl",
            "onetus", "rassel", "obrazgos",
            "drevnerusj", "kategoriinas", "mezhnar",
            "krech","kulrus",
            "novikiev", "yarslov", "rusrazdrob", "prichrasp", "monomah",
            "mongol", "protivagrass", "kakita", "donsk", "centrzeme",
            "otiga", "vosteco", "kolonii", "formzemlv", "gorod",
            "rolczer", "kultrazv", "stanovleniu", "obrazross", "bani",
            "pech ", "hataa", "kostum","kuhn", "muz",
            "tvorch", "obucha", "scaz", "tanzy", "folklor"};

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
        View view = inflater.inflate(R.layout.activity_poiski, container, false);

        listView = view.findViewById(R.id.asdddd);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("РУСЬ");
        for (int i = 0; i < title.length; i++) {
            modeltwo modeltwo = new modeltwo(title[i], description[i]);

            arrayLista.add(modeltwo);
        }


        adapter = new ListViewTwo(getContext(), arrayLista);

        listView.setAdapter(adapter);


        return view;

    }



}