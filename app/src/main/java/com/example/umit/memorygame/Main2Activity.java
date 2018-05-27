package com.example.umit.memorygame;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Timer;

public class Main2Activity extends AppCompatActivity {
    int sonKart=0;
    int skor = 0;
    int hata = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();

        final String s  = i.getStringExtra("mesaj");//array olarak geçseydi array olarak alıcaktık
        // ama tek bir tane mesaj alınacak, oyüzden Extra oldu
        //"mesaj" bir önceki sayfada bahsettiğimiz anahtar kelime.
        //final olmasının sebbei listener inline, classs içinde class


        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText("Kullanıcı İsmi: " + s);//Textview ID al, s string bilgiyi ekranda o ID de göster, kullanıcı adı


        GridLayout gl = (GridLayout) findViewById(R.id.kartlar);//GridLayout ID al, bu sayede o ID de kartları göster

        kart kartlar[] = new kart[16];//4x4 toplam 16 kart var.

        for(int j =1;j<=16;j++) {
            kartlar[j-1]=new kart(this, j);//Constractor çağırdık, kartları sıraya koyduk.kaçıncı kart olduğunu bilsin.

            kartlar[j-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final kart k = (kart)v;// hangi kart üzerine tıklandıysa ters çevir.
                    k.cevir();// hangi kart üzerine tıklandıysa ters çevir.
                    if(sonKart>0)// BENDEN ÖNCE ÇEVRİLEN BİR KART VAR MI?
                    {
                        final kart k2 = (kart)findViewById(sonKart); // FİNAL İÇLERİNE BİR KERE DEĞER ATANIYOR
                        //SONRA BİR DAHA DEĞİŞTİRİLEMİYOR.

                        if(k2.onPlanID == k.onPlanID && k2.getId()!= k.getId()){

                        //k2.getId()!= k.getId() aynı butona iki kere tıklanırsa hile olur.
                        //resim IDler eşleşsin, buton IDler değil.

                            //Eslestiler

                            k2.cevrilebilir=false;//kartlar eşleşti ise tekrar geri çevrilemesin diye
                            k.cevrilebilir=false;//kartlar eşleşti ise tekrar geri çevrilemesin diye
                            skor++;// eşleşme oldu, skoru bir attır
                            TextView tv = (TextView)findViewById(R.id.textView3);
                            tv.setText("Skorunuz:"+skor);

                            if(skor==8){// 8 kart eşleşti ise tüm kartlar bulunmuştur.
                                Intent i =new Intent(Main2Activity.this,Main3Activity.class);
                                i.putExtra("puan",hata);
                                i.putExtra("isim",s);
                                startActivity(i);

                                //oyun bitti
                            }
                        }
                        else {// BEN İLK ÇEVRİLEN KARTIM

                            // eslesmediler geri cevir 2 karti

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    k.cevir();
                                    k2.cevir();
                                }
                            },500);//eşleşmediler yarım saniye bekle geri çevir

                            hata++;// eşleşme olmadı,  ekrana gönderilecek hata sayisini arttır.

                            TextView tv = (TextView)findViewById(R.id.textView4);
                            tv.setText("Hata Sayisi:"+hata);
                            sonKart=0;//DAHA ÖNCE CEVRİLEN KARTI ALGILAMAK ÇİN
                        }
                    }
                    else{
                        sonKart = k.getId();
                    }
                }
            });
        }
        //Karistir

        for(int j=0;j<16;j++)
        {

            int rg = (int)(Math.random()*16);//Math.random 0 ile 1 arasında ayı üretir.
            // 16 ile çarparak 1 ile 16 arasında sayi üretmiş olduk.

            kart k = kartlar[rg];  // shuffle :)
            kartlar[rg]=kartlar[j];
            kartlar[j]=k;
        }


        for(int j =0;j<16;j++)
            gl.addView(kartlar[j]);

    }
}
