package com.example.umit.memorygame;


/**
 * Created by umitliguler on 27/05/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;



public class kart extends android.support.v7.widget.AppCompatButton{
    boolean acikMi = false;
    boolean cevrilebilir = true;
    int arkaPlanID;
    int onPlanID=0;
    Drawable on;
    Drawable arka;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)  //derleyici zorla koyduruyor :)
    @SuppressLint("RestrictedApi")                      //derleyici zorla koyduruyor :)
    public kart(Context context, int id) {              // Constractor
        super(context);
        setId(id);
        arkaPlanID = R.drawable.star;                   //drawable klasöründen çekiyoruz, IDsini alıyoruz
        if(id%8==1)                                     //herbirnin unique IDsi var
            onPlanID = R.drawable.c1;
        if(id%8==2)
            onPlanID = R.drawable.c2;
        if(id%8==3)
            onPlanID = R.drawable.c3;
        if(id%8==4)
            onPlanID = R.drawable.c4;
        if(id%8==5)
            onPlanID = R.drawable.c5;
        if(id%8==6)
            onPlanID = R.drawable.c6;
        if(id%8==7)
            onPlanID = R.drawable.c7;
        if(id%8==0)
            onPlanID = R.drawable.c0;
        arka = AppCompatDrawableManager.get().getDrawable(context,arkaPlanID);//ID değerine göre klasörden resmi çektik..
        on= AppCompatDrawableManager.get().getDrawable(context,onPlanID);//ID değerine göre klasörden resmi çektik..
        setBackground(arka);//Klasörden çektiğimiz resmi arkaplan resmi yaptık.


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void cevir(){
        if(cevrilebilir) {
            if (!acikMi) { // arkasi cevriliyse
                setBackground(on);//Klasörden çektiğimiz resmi ön resmi yaptık.
                acikMi = true;
            } else {
                setBackground(arka);//Klasörden çektiğimiz resmi arkaplan resmi yaptık.
                acikMi = false;//çünkü sayıları göstermek istemiyoruz, yıldız yaptık
            }
        }
    }

}