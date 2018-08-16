package com.tiquay.hoctienganhfree;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.tiquay.hoctienganhfree.model.ChapTruyen;


import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listChap;
    ArrayList<ChapTruyen> arrayMuc;
    Chapadapter adapter;
    private String DATABASE_NAME = "TyQuay.sqlite";
    SQLiteDatabase database;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        MobileAds.initialize(this,
                "ca-app-pub-9718414910847389~5864446254");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9718414910847389/9677327430");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        listChap = findViewById(R.id.lvChap);
        arrayMuc = new ArrayList<>();
        getData();
        listChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mInterstitialAd.show();
                arrayMuc.get(position).setHinh(R.drawable.bullbasaur);
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("id",arrayMuc.get(position).getMaChap());
                intent.putExtra("name", arrayMuc.get(position).getTenChapTV());
                startActivity(intent);
            }
        });
    }
    private void getData(){
        int id = 0;
        String maChap = null;
        String tenChap = null;
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM chaptruyen ", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            id = cursor.getInt(0);
            tenChap = cursor.getString(1).trim();
            arrayMuc.add(new ChapTruyen(id, tenChap, R.drawable.ultraball));
        }
        adapter = new Chapadapter(R.layout.chap_truyen, Main2Activity.this, arrayMuc);
        listChap.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        MobileAds.initialize(this,
                "ca-app-pub-9718414910847389~5864446254");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9718414910847389/9677327430");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();
    }
}
