package com.tiquay.hoctienganhfree;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tiquay.hoctienganhfree.model.NoiDungTruyen;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{
private int id;
    private String name;
    private String DATABASE_NAME = "TyQuay.sqlite";
    SQLiteDatabase database;
    ArrayList<NoiDungTruyen> list;
    ImageView imgTruyen, imgPri, imgNext;
    int location = 0;
    TextView txtPage;
    PhotoViewAttacher mViewAttacher;
    SeekBar seekBar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        MobileAds.initialize(this,
                "ca-app-pub-9718414910847389~5864446254");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        imgTruyen = findViewById(R.id.imgTruyen);
        imgPri = findViewById(R.id.imgPrivious);
        imgNext = findViewById(R.id.imgnext);
        txtPage = findViewById(R.id.txtpage);
        seekBar = findViewById(R.id.seekbarlist);
        seekBar.setEnabled(false);
        imgPri.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        list = new ArrayList<>();

        getData();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void getData(){

        String viet = null;
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM noidung", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            viet = cursor.getString(2).trim();
            list.add(new NoiDungTruyen(id, viet));


        }

        Picasso.with(this).load(list.get(0).getViet()).into(imgTruyen, new Callback() {
            @Override
            public void onSuccess() {
                mViewAttacher = new PhotoViewAttacher(imgTruyen);
            }

            @Override
            public void onError() {

            }
        });
        txtPage.setText("Trang "+(location+1)+"/"+list.size());
   }
   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgnext:
                if (location < list.size()-1) {
                    location++;
                    Picasso.with(this).load(list.get(location).getViet()).into(imgTruyen, new Callback() {
                        @Override
                        public void onSuccess() {
                            mViewAttacher = new PhotoViewAttacher(imgTruyen);
                            seekBar.setProgress((100/list.size())*location);
                        }

                        @Override
                        public void onError() {

                        }
                    });
                    txtPage.setText("Trang "+(location+1)+"/"+list.size());
                }
                break;
            case R.id.imgPrivious:
                if (location > 0){
                    location--;
                    Picasso.with(this).load(list.get(location).getViet()).into(imgTruyen, new Callback() {
                        @Override
                        public void onSuccess() {
                            mViewAttacher = new PhotoViewAttacher(imgTruyen);
                            seekBar.setProgress((100/list.size())*location);
                        }

                        @Override
                        public void onError() {

                        }
                    });
                    txtPage.setText("Trang "+(location+1)+"/"+list.size());
                }

                break;
        }


    }
}
