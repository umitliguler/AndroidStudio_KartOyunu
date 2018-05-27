package com.example.umit.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText et = (EditText) findViewById(R.id.editText);//final yazmak zorundayız yoksa listener içinde kullanamayız

//içeriye erişebilmek için


//listener inline olarak tanımlandı, yani inner class aslında. İçine erişimler zor,
//çünkü sınıf içinde bir sınıf tanımlanmış oluyor.
        ((EditText) findViewById(R.id.editText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //Butona basıldımı?
              // if((et.getText().toString()) == ("Kullanıcı İsmi: ".toString()))
                et.setText("   ");                                                //Sürekli dinlemede kalıyor.

                    //Sürekli dinlemede kalıyor.
            }
        });


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       //Butona basıldımı?
                Intent i = new Intent (MainActivity.this,Main2Activity.class);  //Sürekli dinlemede kalıyor.
                //yeni bir sayfa açmak için yeni bir intent oluşturuyoruz,

                i.putExtra("mesaj",et.getText().toString()); //Bir sonraki ekranda kullanıcı ismini gösterebilemek adına yazıldı.
// mesaaj yazısı bir şifre gibi iki tarafında bildiği bir anahtar kelime, bu sayede  burda doldurma, diğer tarafta bilgi alma işlmi yapılabiliyor.
// et.getText() geçireceğimiz bilgi alınıyor. editText den gelen string mesaj.
                startActivity(i); //Bir sonraki ekranı başlat.
            }
        });



    }
}
