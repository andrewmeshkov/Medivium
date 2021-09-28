package com.kstudio.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kstudio.quizapp.adapters.ScoreActivityAdapter;
import com.kstudio.quizapp.model.Statistics;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StaticsFragment extends Fragment {


    private FirebaseUser firebaseUser;
    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.menu_score);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_score, container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.progress_dialog);
        final Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        mAdView = view.findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        DatabaseReference databaseReference;

        final List<Statistics> list = new ArrayList<>();

        final RecyclerView recyclerView;

        final RecyclerView.Adapter[] adapter = new RecyclerView.Adapter[1];

        recyclerView = view.findViewById(R.id.recyclerViewS);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter[0]);

        adapter[0] = new ScoreActivityAdapter(StaticsFragment.this, list);

        recyclerView.setAdapter(adapter[0]);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Statistics").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Statistics st = dataSnapshot.getValue(Statistics.class);
                    dialog.dismiss();
                    list.add(st);
                }
                Collections.reverse(list);
                adapter[0] = new ScoreActivityAdapter(StaticsFragment.this, list);

                recyclerView.setAdapter(adapter[0]);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        

        return view;
    }



}
