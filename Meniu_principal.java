package com.example.proiect_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Meniu_principal extends AppCompatActivity {
    public Button adauga,out;
    public Button vezi;
    TextView welcome ;
    String nume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_principal);
        welcome = (TextView) findViewById(R.id.welcome);
        out=findViewById(R.id.log_out);
        adauga = (Button) findViewById(R.id.adauga);
        vezi = (Button) findViewById(R.id.oleaca);
        String wel = getIntent().getStringExtra("numeDB");

        //  String numerar = getIntent().getStringExtra("numerar1");
        //String numerar1=getIntent().getStringExtra("numerar");
        //String banca1 = getIntent().getStringExtra("banca1");
        //String venit1 = getIntent().getStringExtra("venit1");

        welcome.setText("Bun venit, " + wel);
     //   System.out.println(venit1 + banca1+ numerar);

        //String numeDb=getIntent().getStringExtra("numafromdb");
        adauga.setOnClickListener(view -> openactivity(wel));
        vezi.setOnClickListener(view -> openactivity2(wel));
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

    }
    public void openactivity(String key){

        //Intent aici = new Intent(Meniu_principal.this,Add_buget.class);
        cauta_datele(key);
        //startActivity(aici);



    }

    public void close(){
        Intent intent=new Intent(Meniu_principal.this,Log_in.class);
        startActivity(intent);
    }

    public void openactivity2(String key){
        cauta_datele2(key);

    }
    public void cauta_datele(String wel){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query ref=reference.orderByChild("nume").equalTo(wel);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Toast.makeText(MainActivity.this, "aici ", Toast.LENGTH_LONG).show();

                    String nameFromDB = snapshot.child(wel).child("nume").getValue(String.class);
                    String numerarFromDB = snapshot.child(wel).child("numerar").getValue(String.class);
                    String veniDB = snapshot.child(wel).child("venit").getValue(String.class);
                    String bancaDBsuma = snapshot.child(wel).child("banca").getValue(String.class);
                    String key_banca=snapshot.child(wel).child("banca").child("key").getValue(String.class);
                    String cheltuiala=snapshot.child(wel).child("cheltuiala").getValue(String.class);
                    System.out.println(numerarFromDB);
                    System.out.println(nameFromDB);
                    System.out.println(veniDB);
                   // System.out.println(bancaDB);

                    Intent aici=new Intent(Meniu_principal.this,Add_buget.class);
                    aici.putExtra("numerar_mem",numerarFromDB);
                    aici.putExtra("venit",veniDB);
                    aici.putExtra("banca_suma12",bancaDBsuma);
                    aici.putExtra("numeDB",nameFromDB);
                    aici.putExtra("key_banca",key_banca);
                    aici.putExtra("cheltuiala_veche",cheltuiala);
                    startActivity(aici);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }
    public void cauta_datele2(String wel){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query ref=reference.orderByChild("nume").equalTo(wel);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //Toast.makeText(MainActivity.this, "aici ", Toast.LENGTH_LONG).show();

                    String nameFromDB = snapshot.child(wel).child("nume").getValue(String.class);
                    String numerarFromDB = snapshot.child(wel).child("numerar").getValue(String.class);
                    String veniDB = snapshot.child(wel).child("venit").getValue(String.class);
                    String bancaDBsuma = snapshot.child(wel).child("banca").getValue(String.class);
                    String key_banca=snapshot.child(wel).child("banca").child("key").getValue(String.class);
                    String cheltuiala12=snapshot.child(wel).child("cheltuiala").getValue(String.class);
                    System.out.println(numerarFromDB);
                    System.out.println(nameFromDB);
                    System.out.println(veniDB);
                    // System.out.println(bancaDB);

                    Intent xx=new Intent(Meniu_principal.this,Vezi.class);
                    xx.putExtra("numerar_mem",numerarFromDB);
                    xx.putExtra("venit",veniDB);
                    xx.putExtra("banca_suma12",bancaDBsuma);
                    xx.putExtra("numeDB",nameFromDB);
                    //xx.putExtra("key_banca",key_banca);
                    xx.putExtra("cheltuiala_veche",cheltuiala12);
                    startActivity(xx);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

}


//gata