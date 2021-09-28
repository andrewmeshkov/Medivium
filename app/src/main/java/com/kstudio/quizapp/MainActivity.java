package com.kstudio.quizapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.kstudio.quizapp.chat.ChatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser firebaseUser;
    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private DatabaseReference myRef;
    private StorageReference firebaseStorage;
    private boolean isAdmin=false;


    public CircleImageView imageView1;
    final long ONE_MEGABYTE = 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        auth= FirebaseAuth.getInstance();

        View headerView = navigationView.getHeaderView(0);
        TextView navEmail = headerView.findViewById(R.id.tvEmail);
        navEmail.setText(firebaseUser.getEmail());
        imageView1 = (navigationView.getHeaderView(0)).findViewById(R.id.iaamageView);

        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new europetest();
        displaySelectedFragment(fragment);
        checkForAdmin();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        setImageOnNavHeader();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
         if (id == R.id.nav_score) {
            fragment = new StaticsFragment();
            displaySelectedFragment(fragment);
        }else if(id == R.id.ege) {
            fragment = new egest();
            displaySelectedFragment(fragment);


        }
         else if(id == R.id.oge) {
             fragment = new ogest();
             displaySelectedFragment(fragment);


         }else if(id == R.id.rusobsh) {
             fragment = new rus();
             displaySelectedFragment(fragment);


         }
         else if(id == R.id.chat) {
             startActivity(new Intent(MainActivity.this, ChatActivity.class));
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
         }
         else if(id == R.id.sertest) {
                 startActivity(new Intent(MainActivity.this, usertest.class));
                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


         }
         else if(id == R.id.make) {
             if (isAdmin && isNetworkAvailable(MainActivity.this)) {
                 startActivity(new Intent(MainActivity.this, createquiz.class));
                 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
             }
             else if (isNetworkAvailable(MainActivity.this))
                 Toasty.error(getApplicationContext(), "ВЫ НЕ АДМИНИСТРАТОР ", Toasty.LENGTH_SHORT).show();

         }
         else if(id == R.id.usertestsstat) {
            Intent intent = new Intent(MainActivity.this, resultsadministrator.class);
            intent.putExtra("ISADMIN",isAdmin);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);



        }


         else if(id == R.id.rus) {
             fragment = new rusteor();
             displaySelectedFragment(fragment);


         }else if(id == R.id.nav_home) {
             fragment = new europetest();
             displaySelectedFragment(fragment);


         }
         else if(id == R.id.eur) {
             fragment = new pois();
             displaySelectedFragment(fragment);


         } else if (id == R.id.nav_setting) {
             fragment = new settingsfragment();
             displaySelectedFragment(fragment);
        } else if (id == R.id.nav_about) {
            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            adb.setMessage(R.string.about);
            adb.setIcon(R.drawable.log);

            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alert = adb.create();
            alert.setTitle(" ");
            alert.show();

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        }
         else if (id == R.id.thisdayinhistory) {

             Date currentDate = new Date();
             DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
             String dateText = dateFormat.format(currentDate);

             if (dateText.equals("26.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "26 МАЯ");
                 intent.putExtra("contentTv","1232 — Папа Григорий IX послал первых инквизиторов в Арагон, положив начало тому, что через два века войдёт в историю под названием Испанская инквизиция.\n" +
                         "1521 — император Карл V подписал Вормсский эдикт, предписывавший арест религиозного реформатора Мартина Лютера.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }
             else if (dateText.equals("25.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","996 — в Киеве освящена первая на Руси каменная церковь во имя Успения Пресвятой Богородицы — знаменитая Десятинная церковь." +
                         " 1085 — Альфонсо VI Храбрый отвоевал Толедо у мусульман.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "27 МАЯ");
                 intent.putExtra("contentTv","1199 — римский папа Иннокентий обложил первым прямым папским налогом Духовенство.\n" +
                         "1328 — после смерти младшего сына Филиппа IV Красивого — Карла IV Красивого, который стал последним представителем династии Капетингов, на французский престол вступил двоюродный брат Карла Филипп VI — первый король из династии Валуа.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }
             else if (dateText.equals("28.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "28 МАЯ");
                 intent.putExtra("contentTv","1539 — испанский конкистадор и кубинский губернатор Эрнандо де Сото вошёл в залив Тампа у берегов Флориды. Его шеститысячный отряд пересек территорию современного штата Джорджия и к маю 1542 года достиг Миссисипи.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }
             else if (dateText.equals("29.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "29 МАЯ");
                 intent.putExtra("contentTv","1176 — произошла битва при Леньяно, ополчение Ломбардской лиги разгромило войска императора Священной Римской империи Фридриха Барбароссы.\n" +
                         "1453 — турецкая армия под начальством султана Мехмеда II захватывает Константинополь.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }
             else if (dateText.equals("30.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "30 МАЯ");
                 intent.putExtra("contentTv","1416 — инквизицией сожжён чешский религиозный реформатор Иероним Пражский." +
                         "1431 — в Руане сожжена Жанна д’Арк." +
                         "1434 — Гуситские войны: произошла Битва у Липан.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("31.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "31 МАЯ");
                 intent.putExtra("contentTv","1223 — В битве при Калке войсками монгольского полководца Субэдея разбито русско-половецкое войско.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("01.06.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "1 ИЮНЯ");
                 intent.putExtra("contentTv","1252 — Альфонсо X становится королём Кастилии и Леона.\n" +
                         "1298 — произошло Сражение при Трейдене между войском Ливонского ордена и войском Великого княжества Литовского.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("02.06.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "2 ИЮНЯ");
                 intent.putExtra("contentTv","1098 — взятие крестоносцами Антиохии.\n" +
                         "1326 — между Новгородской республикой и королём Магнус Эрикссоном подписан договор разграничивающий приграничные территории и устанавливающий свободу торговли.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("03.06.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "3 ИЮНЯ");
                 intent.putExtra("contentTv","1183 — в ходе Войны Гэмпэй произошла Битва при Курикара (англ.)русск..\n" +
                         "1474 — рухнул незавершённый Успенский собор в Московском Кремле, возводимый мастерами Кривцовым и Мышкиным.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }else if (dateText.equals("27.05.2020")){
                 Intent intent = new Intent(MainActivity.this, thisd.class);
                 intent.putExtra("actionBarTitle",   "25 МАЯ");
                 intent.putExtra("contentTv","н.");
                 startActivity(intent);
                 overridePendingTransition(R.anim.open_next, R.anim.close_next);
             }

         }else if (id == R.id.nav_exit) {
            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
            adb.setMessage("ВЫ ХОТИТЕ ЗАКРЫТЬ ПРИЛОЖЕНИЕ ?");

            adb.setPositiveButton("НЕТ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

            adb.setNegativeButton("ДА", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog alert = adb.create();
            alert.setTitle("ВЫХОД ИЗ ПРИЛОЖЕНИЯ");
            alert.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
    public void setImageOnNavHeader() {

        firebaseStorage = FirebaseStorage.getInstance().getReference(Objects.requireNonNull(auth.getUid()));
        firebaseStorage.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        imageView1.setMinimumHeight(90);
                        imageView1.setMinimumWidth(90);
                        imageView1.setMaxHeight(100);
                        imageView1.setMaxWidth(100);
                        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView1.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void checkForAdmin() {

        myRef.child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(Objects.requireNonNull(auth.getUid()))
                        .exists()&& Objects.requireNonNull(dataSnapshot.child(auth.getUid())
                        .getValue()).toString().equals("true")){
                    isAdmin=true;
                    Toasty.info(getApplicationContext(),"ПРИВЕТ АДМИН", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    }
