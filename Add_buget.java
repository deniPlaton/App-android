package com.example.proiect_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicReference;

public class Add_buget extends AppCompatActivity {
    public Button inapoi ;
    public Button venituri;
    public Button cheltuieli;
    public Button numerar;
    public Button banca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buget);
        String key=getIntent().getStringExtra("numeDB");
        String suma= getIntent().getStringExtra("numerar_mem");
        String venit12=getIntent().getStringExtra("venit");
        String bancasuma1=getIntent().getStringExtra("banca_suma12");
        String cheltuiala_initiala=getIntent().getStringExtra("cheltuiala_veche");

        inapoi= (Button) findViewById(R.id.inapoi1);
        venituri=(Button) findViewById(R.id.venituri);
        cheltuieli=(Button) findViewById(R.id.cheltuieli);
        numerar=(Button) findViewById(R.id.cash);
        banca=(Button) findViewById(R.id.cont_bancar_nou);
       // System.out.println(suma);


    inapoi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Open1(key);

        }
    });
    venituri.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Open7(key,
                   suma,
                   bancasuma1,
                   venit12);
        }
    });
    cheltuieli.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Open3(key,suma,bancasuma1,venit12,cheltuiala_initiala);
        }
    });
    numerar.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Open4(key, suma,venit12);
        }
    });
    banca.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Open6(key,bancasuma1);
        }
    });
    }
public void Open1(String numeDB){
    Intent intent= new Intent(this, Meniu_principal.class);
    intent.putExtra("numeDB",numeDB);
    startActivity(intent);
}
    public void Open7(String key,String numerar,String banca, String venit){
        Intent sss=new Intent(this, Adaugavenituri.class);
        sss.putExtra("key_venit",key);
        sss.putExtra("numerar",numerar);
        sss.putExtra("venit",venit);
        sss.putExtra("bancasuma",banca);
        startActivity(sss);
    }
    public void Open3(String key,String numerar,String banca,String venit,String cheltuiala1){
        Intent intent= new Intent(this, Adaugacheltuieli.class);
        intent.putExtra("key_venit1",key);
        intent.putExtra("numerar1",numerar);
        intent.putExtra("venit1",venit);
        intent.putExtra("bancasuma1",banca);
        intent.putExtra("cheltuiala_veche",cheltuiala1);

        startActivity(intent);
    }
    public void Open4(String key,String numerar,String venit){
        Intent intent= new Intent(this, Adauganumerar.class);
        intent.putExtra("nume_numeral",key);
        intent.putExtra("venit_initial",venit);
        intent.putExtra("numerar_add",numerar);
        startActivity(intent);
    }
    public void Open6(String key , String banca){

        Intent intent= new Intent(this,Adauga_banca.class);
        intent.putExtra("nume",key);
        intent.putExtra("banca",banca);
        startActivity(intent);
    }

}
//gata
