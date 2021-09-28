package com.kstudio.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewTwo extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<modeltwo> modellist;
    ArrayList<modeltwo> arrayLista;
    private AdView mAdView;
    GridView androidGridView;
    public static final String CAT = "com.kstudio.sliitquizapp.SUBJECT";
    String category="";

    public ListViewTwo(Context context, List<modeltwo> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayLista = new ArrayList<modeltwo>();
        this.arrayLista.addAll(modellist);
    }

    public static class ViewHolder{
        public TextView mTitleTv,mDescTv;

    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return modellist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        final ListViewTwo.ViewHolder holder;
        if (view==null){
            holder = new ListViewTwo.ViewHolder();
            view = inflater.inflate(R.layout.rowtw, null);

            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);

            view.setTag(holder);

        }
        else {
            holder = (ListViewTwo.ViewHolder)view.getTag();
        }
        holder.mTitleTv.setText(modellist.get(postition).getS());
        holder.mDescTv.setText(modellist.get(postition).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (modellist.get(postition).getDesc().equals("glaeur")){
                    Intent intent = new Intent(mContext, glavniesob.class);


                    mContext.startActivity(intent);


                }
                if (modellist.get(postition).getDesc().equals("poznyy")){

                    Intent intent = new Intent(mContext, poznya.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("peresel")){
                    Intent intent = new Intent(mContext, pereselenie.class);
                   mContext.startActivity(intent);
                }

                if (modellist.get(postition).getDesc().equals("varvar")){
                    Intent intent = new Intent(mContext, varvar.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("vizan")){
                    Intent intent = new Intent(mContext, vizantia.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("christ")){
                    Intent intent = new Intent(mContext, cristh.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("frank")){
                    Intent intent = new Intent(mContext, frank.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("rekon")){
                    Intent intent = new Intent(mContext, rekon.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("feod")){
                    Intent intent = new Intent(mContext, feodalizm.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("pohod")){
                    Intent intent = new Intent(mContext, pohodu.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("cze")){
                    Intent intent = new Intent(mContext, czerkov.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("shol")){
                    Intent intent = new Intent(mContext, sholastika.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("torgikom")){
                    Intent intent = new Intent(mContext, torgovla.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("arab")){
                    Intent intent = new Intent(mContext, arab.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("hpolyrom")){
                    Intent intent = new Intent(mContext, holyroman.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("naitech")){
                    Intent intent = new Intent(mContext, naukaitech.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("stovoyna")){
                    Intent intent = new Intent(mContext, stowar.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("osman")){
                    Intent intent = new Intent(mContext, osman.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("reform")){
                    Intent intent = new Intent(mContext, reform.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("renness")){
                    Intent intent = new Intent(mContext, rennes.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("harachert")){
                    Intent intent = new Intent(mContext, harakchertu.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("medi")){
                    Intent intent = new Intent(mContext, med.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("odezda")){
                    Intent intent = new Intent(mContext, odezda.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("zhivopis")){
                    Intent intent = new Intent(mContext, zhivopis.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("miniatur")){
                    Intent intent = new Intent(mContext, mini.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("skulpt")){
                    Intent intent = new Intent(mContext, skulptura.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("gotika")){
                    Intent intent = new Intent(mContext, gotika.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("roamans")){
                    Intent intent = new Intent(mContext, romanstile.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("glarus")) {
                    Intent intent = new Intent(mContext, glarus.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals( "drevn")){
                    Intent intent = new Intent(mContext, drevnie.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("vostsl")){

                    Intent intent = new Intent(mContext, vostslav.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("onetus")){

                    Intent intent = new Intent(mContext, onetus.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals( "rassel")){
                    Intent intent = new Intent(mContext, rasselenie.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("obrazgos")){
                    Intent intent = new Intent(mContext, obrazgos.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("drevnerusj")){
                    Intent intent = new Intent(mContext, drevnerusgos.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kategoriinas")){
                    Intent intent = new Intent(mContext, kategoriinas.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("mezhnar")){
                    Intent intent = new Intent(mContext, mezhnar.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("krech")){
                    Intent intent = new Intent(mContext, krechenie.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kulrus")){
                    Intent intent = new Intent(mContext, kulrus.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals( "novikiev")){
                    Intent intent = new Intent(mContext, novgorodkiev.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("yarslov")){
                    Intent intent = new Intent(mContext, yaroslav.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("rusrazdrob")){
                    Intent intent = new Intent(mContext, razdrob.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("prichrasp")){
                    Intent intent = new Intent(mContext, prichrasp.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("monomah")){
                    Intent intent = new Intent(mContext, monomah.class);
                  mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("mongol")){
                    Intent intent = new Intent(mContext, mongolzav.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("protivagrass")){
                    Intent intent = new Intent(mContext, vneshaggression.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kakita")){
                    Intent intent = new Intent(mContext, kalita.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("donsk")){
                    Intent intent = new Intent(mContext, donskoy.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("centrzeme")){
                    Intent intent = new Intent(mContext, moscowcentr.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("otiga")){
                    Intent intent = new Intent(mContext, otiga.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("vosteco")){
                    Intent intent = new Intent(mContext, vosstanovlenieekono.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kolonii")){
                    Intent intent = new Intent(mContext, kolonii.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("formzemlv")){
                    Intent intent = new Intent(mContext, fromzemlevlad.class);
                  mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("gorod")){
                    Intent intent = new Intent(mContext, gorod.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("rolczer")){
                    Intent intent = new Intent(mContext, rolcerkvi.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kultrazv")){
                    Intent intent = new Intent(mContext, kultrazv.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("stanovleniu")){
                    Intent intent = new Intent(mContext, stanovleniegos.class);
                  mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("obrazross")){
                    Intent intent = new Intent(mContext, obrazgosvaros.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("bani")){
                    Intent intent = new Intent(mContext, bani.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("pech ")){
                    Intent intent = new Intent(mContext, pech.class);
                   mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("hataa")){
                    Intent intent = new Intent(mContext, dom.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kostum")){
                    Intent intent = new Intent(mContext, kostum.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("kuhn")){
                    Intent intent = new Intent(mContext, kuhny.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("muz")){
                    Intent intent = new Intent(mContext, muzuka.class);
                  mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals( "tvorch")){
                    Intent intent = new Intent(mContext, tvorchestvo.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("obucha")){
                    Intent intent = new Intent(mContext, obuchai.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("scaz")){
                    Intent intent = new Intent(mContext, scazki.class);
                     mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("tanzy")){
                    Intent intent = new Intent(mContext, tanzu.class);
                    mContext.startActivity(intent);
                }
                if (modellist.get(postition).getDesc().equals("folklor")){

                    Intent intent = new Intent(mContext, folklor.class);
                    mContext.startActivity(intent);
                }


            }
        });


        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modellist.clear();
        if (charText.length()==0){
            modellist.addAll(arrayLista);
        }
        else {
            for (modeltwo modeltwo : arrayLista){
                if (modeltwo.getS().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    modellist.add(modeltwo);
                }
            }
        }
        notifyDataSetChanged();
    }

}
