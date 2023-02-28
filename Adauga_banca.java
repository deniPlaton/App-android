package com.example.proiect_java;


import static com.example.proiect_java.R.array.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.rpc.context.AttributeContext;

import org.checkerframework.checker.units.qual.C;

import io.grpc.internal.SharedResourceHolder;

public class Adauga_banca extends AppCompatActivity {
    public Button anuleaza, adauga;
    public EditText den_banca,cod_plata,suma;
    String key;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Istoric");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_banca);
        String[] denumire;
        anuleaza=(Button) findViewById(R.id.anuleaza);
        adauga=findViewById(R.id.adauga_banca);
        den_banca=findViewById(R.id.nume_banca);
        cod_plata=findViewById(R.id.cod_Iban);
        suma=(EditText) findViewById(R.id.suma_banca_adaugata);
        //key=getIntent().getStringExtra("key_banca");



        String suma_initiala=getIntent().getStringExtra("banca");
        System.out.println("este" + suma_initiala);
        String user=getIntent().getStringExtra("nume");
        System.out.println(user);
        String key = getIntent().getStringExtra("key_banca");
        System.out.println(key);
        String key_nominal=verifcare(key);
        System.out.println(key_nominal);
        anuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   cancel(user);
            }
        });

        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateden() | !validareSuma() | !cod_plata()){
              String suma1=suma.getText().toString();
              String den=den_banca.getText().toString();
              String cd_pl=cod_plata.getText().toString();
              int suma_banca=Convert(suma1);
              int suma_veche=Convert(suma_initiala);
              System.out.println("II" + suma_banca);
              //int suma_veche= Convert(suma_initiala);

                if (isNumerarChanged(user, suma_veche,key_nominal, suma_banca,den,cd_pl)) {
                    Toast.makeText(Adauga_banca.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Adauga_banca.this, Meniu_principal.class);
                    intent.putExtra("numeDB",user);
                    intent.putExtra("key_banca",key_nominal);
                    startActivity(intent);
                } else {
                    Toast.makeText(Adauga_banca.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }}
        });

    }
    @SuppressLint("ResourceType")
    private boolean isNumerarChanged (String usernameUser, int suma_v, String key, int suma_n, String den, String cd_pl) {
        int total = 0;
        total = suma_v + suma_n;
        System.out.println(total);
        String total1 = Convert_string(total);

        System.out.println("este " + total1);
        //Toast.makeText(Adauga_banca.this, "aici", Toast.LENGTH_SHORT).show();
        System.out.println(key);
        if (suma_v != total) {

            System.out.println(usernameUser);
            reference.child(usernameUser).child("banca").setValue(total1);
            //Toast.makeText(Adauga_banca.this, "aici", Toast.LENGTH_SHORT).show();
           // reference.child(usernameUser).child("banca").child(key).child("key").setValue(key);



            //reference.child("Istoric").child(usernameUser).child("numerar_add").setValue(total1);}
           // Toast.makeText(Adauga_banca.this, "aici", Toast.LENGTH_SHORT).show();
            //numerarUser = cash.getText().toString();

            return true;
        } else {


            return false;
        }
    }
    private String verifcare(String key){
        if (key != null){
            int key1=Convert(key);
            key1=key1+1;
            String key2=Convert_string(key1);
            return key2;
        }else{
            key="1";
            return  key;
        }
    }

    private int Convert(String conv){

        try {
            int total = Integer.parseInt(conv);
            //Toast.makeText(Adauga_banca.this, "aici", Toast.LENGTH_SHORT).show();
            return total;
        }catch (NumberFormatException e){
            return 0;
        }
    }
    public void cancel(String user){
        Intent intent= new Intent(this, Add_buget.class);
        intent.putExtra("numeDb",user);
        //intent.putExtra("key_banca",key);
        startActivity(intent);
    }
    private String Convert_string(int total){
        String total_numerar=String.valueOf(total);
        return total_numerar;
    }
    public Boolean cod_plata() {
        String val = cod_plata.getText().toString();
        if (val.isEmpty()) {
            cod_plata.setError("Cod plata cannot be empty");
            return false;
        } else {
            cod_plata.setError(null);
            return true;
        }
    }
    public Boolean validateden() {
        String val = den_banca.getText().toString();
        if (val.isEmpty()) {
            den_banca.setError("Denumire banca cannot be empty");
            return false;
        } else {
            den_banca.setError(null);
            return true;
        }
    }
    public Boolean validareSuma() {
        String val = suma.getText().toString();
        if (val.isEmpty()) {
            suma.setError("Suma cannot be empty");
            return false;
        } else {
            suma.setError(null);
            return true;
        }
    }
}