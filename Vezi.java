package com.example.proiect_java;

import static com.example.proiect_java.R.id.suma_num;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vezi extends AppCompatActivity {
    public Button catre;
    TextView chelt,ven, s_b, s_n,suma_tot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vezi);

        String VENIdb=getIntent().getStringExtra("venit");
        String banca=getIntent().getStringExtra("banca_suma12");
        String nume=getIntent().getStringExtra("numeDB");
        String numerar_DB=getIntent().getStringExtra("numerar_mem");
        String cheltuieli=getIntent().getStringExtra("cheltuiala_veche");

        chelt=findViewById(R.id.cheltuieli_totale);
        ven=findViewById(R.id.venituri_totala);
        s_b=findViewById(R.id.suma_b);
        s_n=findViewById(R.id.suma_num);
        suma_tot=findViewById(R.id.suma_totala);

        chelt.setText(cheltuieli);
        s_b.setText(banca);
        s_n.setText(numerar_DB);
        ven.setText(VENIdb);

        int suma_numerar=Convert(numerar_DB);
        int suma_banca=Convert(banca);
        int total=0;
        total=suma_banca+suma_numerar;
        System.out.println(total);
        String total_total= Convert_string(total);

        suma_tot.setText(total_total);


        catre=(Button) findViewById(R.id.catre);
        catre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catre(nume);
            }
        });

    }
    public void catre(String nume){
        Intent intent= new Intent(Vezi.this,Meniu_principal.class);
        intent.putExtra("numeDB",nume);
        startActivity(intent);
    }

    private int Convert(String conv){

        try {
            int total = Integer.parseInt(conv);
            Toast.makeText(Vezi.this, "aici", Toast.LENGTH_SHORT).show();
            return total;
        }catch (NumberFormatException e){
            return 0;
        }
    }

    private String Convert_string(int total){
        String total_numerar=String.valueOf(total);
        return total_numerar;
    }
}