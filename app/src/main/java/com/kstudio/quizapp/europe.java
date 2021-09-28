package com.kstudio.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class europe extends AppCompatActivity {
TextView r;
TextView q;
TextView p;
TextView a;
TextView m;
TextView w;
    TextView l;
    Button nq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_europe);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");
        r = (TextView) findViewById(R.id.ep);
        r.setTypeface(font);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gerd.ttf");
        TextView rd = (TextView) findViewById(R.id.zad);
        rd.setTypeface(font);
        p = (TextView) findViewById(R.id.earl);
        p.setTypeface(font);
        Button ad=(Button) findViewById(R.id.poz);
        ad.setText("ПОЗДНЯЯ РИМСКАЯ ИМПЕРИЯ");
        ad.setTypeface(typeface);
        Button add=(Button) findViewById(R.id.gla);
        add.setText("ГЛАВНЫЕ СОБЫТИЯ");
        add.setTypeface(typeface);
        Button b=(Button) findViewById(R.id.vel);
        b.setText("ВЕЛИКОЕ ПЕРЕСЕЛЕНИЕ НАРОДОВ");
        b.setTypeface(typeface);
        Button as=(Button) findViewById(R.id.var);
        as.setText("ВАРВАРСКИЕ КОРОЛЕВСТВА");
        as.setTypeface(typeface);
        Button vc=(Button) findViewById(R.id.viz);
        vc.setText("ВИЗАНТИЙСКАЯ ИМПЕРИЯ");
        vc.setTypeface(typeface);
        Button vcs=(Button) findViewById(R.id.ras);
        vcs.setText("РАСПРОСТРАНЕННИЕ ХРИСТИАНСТВА");
        vcs.setTypeface(typeface);
        Button nd=(Button) findViewById(R.id.ara);
        nd.setText("АРАБСКИЙ МИР");
        nd.setTypeface(typeface);
        Button nw=(Button) findViewById(R.id.fra);
        nw.setText("ФРАНКСКОЕ ГОСУДАРСТВО");
        nw.setTypeface(typeface);
        Button nq=(Button) findViewById(R.id.rekoki);
        nq.setText("РЕКОНКИСТА");
        nq.setTypeface(typeface);


        Button nqb=(Button) findViewById(R.id.feo);
        nqb.setText("ФЕОДАЛИЗМ");
        nqb.setTypeface(typeface);
        Button nqn=(Button) findViewById(R.id.raz);
        nqn.setText("РАЗВИТИЕ НАУКИ И ТЕХНОЛОГИЙ");
        nqn.setTypeface(typeface);
        Button nqm=(Button) findViewById(R.id.kre);
        nqm.setText("КРЕСТОВЫЕ ПОХОДЫ");
        nqm.setTypeface(typeface);
        Button nqv=(Button) findViewById(R.id.cze);
        nqv.setText("ЦЕРКОВЬ");
        nqv.setTypeface(typeface);
        Button nqw=(Button) findViewById(R.id.HIR);
        nqw.setText("СВЯЩЕННАЯ РИМСКАЯ ИМПЕРИЯ");
        nqw.setTypeface(typeface);
        Button nqa=(Button) findViewById(R.id.tor);
        nqa.setText("ТОРГОВЛЯ И КОММЕРЦИЯ");
        nqa.setTypeface(typeface);
        Button nqs=(Button) findViewById(R.id.sho);
        nqs.setText("СХОЛАСТИКА");
        nqs.setTypeface(typeface);
        Button nqds=(Button) findViewById(R.id.ode);
        nqds.setText("МЕДЕЦИНА");
        nqds.setTypeface(typeface);
        Button nqas=(Button) findViewById(R.id.med);
        nqas.setText("ОДЕЖДА");
        nqas.setTypeface(typeface);
        Button nqasd=(Button) findViewById(R.id.rm);
        nqasd.setText("ГОТИКА");
        nqasd.setTypeface(typeface);
        Button nqsas=(Button) findViewById(R.id.got);
        nqsas.setText("РОМАНСКИЙ СТИЛЬ");
        nqsas.setTypeface(typeface);
        Button nqsasb=(Button) findViewById(R.id.iko);
        nqsasb.setText("ИКОНОГРАФИЯ");
        nqsasb.setTypeface(typeface);
        Button nqsasc=(Button) findViewById(R.id.sku);
        nqsasc.setText("СКУЛЬПТУРА");
        nqsasc.setTypeface(typeface);
        Button nqsasd=(Button) findViewById(R.id.min);
        nqsasd.setText("МИНИАТЮРА");
        nqsasd.setTypeface(typeface);
        Button nqsasw=(Button) findViewById(R.id.ziv);
        nqsasw.setText("ЖИВОПИСЬ");
        nqsasw.setTypeface(typeface);
        Button no=(Button) findViewById(R.id.sto);
        no.setText("СТОЛЕТНЯЯ ВОЙНА");
        no.setTypeface(typeface);
        Button nqssasw=(Button) findViewById(R.id.har);
        nqssasw.setText("ХАРАКТЕРНЫЕ ЧЕРТЫ");
        nqssasw.setTypeface(typeface);
        Button qnqsasw=(Button) findViewById(R.id.zavoevanir);
        qnqsasw.setText("ЗАВОЕВАНИЕ ОСМАНСКОЙ ИМПЕРИИ");
        qnqsasw.setTypeface(typeface);
        Button wnqsasw=(Button) findViewById(R.id.refor);
        wnqsasw.setText("РЕФОРМАЦИЯ");
        wnqsasw.setTypeface(typeface);
        Button enqsasw=(Button) findViewById(R.id.rennes);
        enqsasw.setText("РЕННЕСАНС");
        enqsasw.setTypeface(typeface);


        w = (TextView) findViewById(R.id.pozdnee);
        w.setTypeface(font);
        l= (TextView) findViewById(R.id.vusokoe);
        l.setTypeface(font);
        q= (TextView) findViewById(R.id.textView3);
        q.setTypeface(font);
        a= (TextView) findViewById(R.id.iskusstvo);
        a.setTypeface(font);
        m= (TextView) findViewById(R.id.archi);
        m.setTypeface(font);
        Button gla = (Button) findViewById(R.id.gla);
        gla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teoria;
                teoria = new Intent(europe.this, glavniesob.class);
                startActivity(teoria);

            }
        });
        Button poz = (Button) findViewById(R.id.poz);
        poz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, poznya.class);
                startActivity(dsd);

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, pereselenie.class);
                startActivity(dsd);

            }
        });
        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, vizantia.class);
                startActivity(dsd);

            }
        });
        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, varvar.class);
                startActivity(dsd);

            }
        });
        vcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, cristh.class);
                startActivity(dsd);

            }
        });
        nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, arab.class);
                startActivity(dsd);

            }
        });
        nw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, frank.class);
                startActivity(dsd);

            }
        });

        nq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, rekon.class);
                startActivity(dsd);

            }
        });
        nqb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, feodalizm.class);
                startActivity(dsd);

            }
        });
        nqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, naukaitech.class);
                startActivity(dsd);

            }
        });
        nqm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, pohodu.class);
                startActivity(dsd);

            }
        });
        nqv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, czerkov.class);
                startActivity(dsd);

            }
        });
        nqw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, holyroman.class);
                startActivity(dsd);

            }
        });
        nqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, torgovla.class);
                startActivity(dsd);

            }
        });
        nqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, sholastika.class);
                startActivity(dsd);

            }
        });
        nqds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, med.class);
                startActivity(dsd);

            }
        });
        nqas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, odezda.class);
                startActivity(dsd);

            }
        });
        nqasd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, gotika.class);
                startActivity(dsd);

            }
        });
        nqsas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, romanstile.class);
                startActivity(dsd);

            }
        });
        nqsasb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, ikonografia.class);
                startActivity(dsd);

            }
        });
        nqsasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, skulptura.class);
                startActivity(dsd);

            }
        });
        nqsasd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, mini.class);
                startActivity(dsd);

            }
        });
        nqsasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, zhivopis.class);
                startActivity(dsd);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, stowar.class);
                startActivity(dsd);

            }
        });
        nqssasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, harakchertu.class);
                startActivity(dsd);

            }
        });
        qnqsasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, osman.class);
                startActivity(dsd);

            }
        });
        wnqsasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, reform.class);
                startActivity(dsd);

            }
        });
        enqsasw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dsd;
                dsd = new Intent(europe.this, rennes.class);
                startActivity(dsd);

            }
        });

    }


}
