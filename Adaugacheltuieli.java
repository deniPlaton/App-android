package com.example.proiect_java;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Adaugacheltuieli extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    EditText data, suma, denumire;
    Spinner tipul_banilor;
    Button adauga,anuleaza;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Istoric");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugacheltuieli);
        //Button anuleaza = (Button) findViewById(R.id.anuleaza);

        anuleaza=findViewById(R.id.anuleaza);
        adauga = findViewById(R.id.adauga_cheltuieli);
        data = findViewById(R.id.data_primiri_cheltuieli);
        denumire = findViewById(R.id.denumire_cheltuieli);
        suma = findViewById(R.id.suma_cheltuieli);
        tipul_banilor = findViewById(R.id.cod_plata_cheltuieli);

        String nume_DB1 = getIntent().getStringExtra("key_venit1");
        String venit_initial = getIntent().getStringExtra("venit1");
        String banca_suma22 = getIntent().getStringExtra("bancasuma1");
        String numerar = getIntent().getStringExtra("numerar1");
        String cheltuiala_initiala = getIntent().getStringExtra("cheltuiala_veche");

        List<tip_plata> tipdeplataList = new ArrayList<>();
        tip_plata tip0=new tip_plata("       ");
        tipdeplataList.add(tip0);
        tip_plata tip1 = new tip_plata("numerar");
        tipdeplataList.add(tip1);
        tip_plata tip2 = new tip_plata("cont banca");
        tipdeplataList.add(tip2);


        ArrayAdapter<tip_plata> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipdeplataList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipul_banilor.setAdapter(adapter);

        tipul_banilor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tip_plata plata = (tip_plata) parent.getSelectedItem();
                getSelect_tip_de_plata(plata);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalendar();
            }

            private void updateCalendar() {
                String Format = "MM/dd/yy";
                SimpleDateFormat std = new SimpleDateFormat(Format, Locale.US);

                data.setText(std.format(calendar.getTime()));
            }
        };
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                new DatePickerDialog(Adaugacheltuieli.this,
                        date,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }

        });


        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateden() | !validatedata() |validareSuma()){
                String suma1 = suma.getText().toString();
                String mod_de_venit = tipul_banilor.toString();
                //String numerar=getIntent().getStringExtra("numerar1");
                //System.out.println(numerar);
                int cheltuiala_veche = Convert(cheltuiala_initiala);
                System.out.println(cheltuiala_veche);

                int suma_plus = Convert(suma1);
                System.out.println(suma_plus);

                int suma_veche_numerar = Convert(numerar);
                System.out.println(suma_veche_numerar);

                int suma_veche_banca = Convert(banca_suma22);
                System.out.println(suma_veche_banca);


                if (isNumerarChanged(nume_DB1, mod_de_venit, suma_veche_numerar, suma_veche_banca, suma_plus, cheltuiala_veche)) {
                    Toast.makeText(Adaugacheltuieli.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Adaugacheltuieli.this, Meniu_principal.class);
                    intent.putExtra("numeDB", nume_DB1);
                    startActivity(intent);
                } else {
                    Toast.makeText(Adaugacheltuieli.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }


                }
            }
        });

        anuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(nume_DB1);
            }

        });
    }

    public void getSelect_tip_de_plata(tip_plata v) {
        tip_plata plata = (tip_plata) tipul_banilor.getSelectedItem();
        cauta(plata);


    }

    public String scoate() {
        tip_plata plata = (tip_plata) tipul_banilor.getSelectedItem();
        String cauta = cauta(plata);
        return cauta;
    }

    public String cauta(tip_plata plata) {
        String tipul_plati = plata.getTip_de_plata();
        String tip = tipul_plati;
        return tip;
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public void cancel(String numeDB) {
        Intent intent = new Intent(this, Add_buget.class);
        intent.putExtra("numeDB", numeDB);
        startActivity(intent);
    }


    //  nume_DB1, suma_veche_numerar, suma_veche_banca, suma_plus,cheltuila_vechi)
    private boolean isNumerarChanged(String usernameUser, String cod_plata, int suma_veche_nr, int suma_veche_bn, int suma_n_add, int cheltuiala_vechi) {
        int dif = 0;
        int suma_noua = 0;
        int suma_nou_banca = 0;
        System.out.println(usernameUser);
        String tip_data = scoate();
        System.out.println(tip_data);
        if (tip_data.equals("numerar")) {
            suma_noua = suma_veche_nr - suma_n_add;
            if (suma_noua >= 0) {
                Toast.makeText(Adaugacheltuieli.this, "Aici este", Toast.LENGTH_SHORT).show();
                String nou_numerar = Convert_string(suma_noua);
                System.out.println(nou_numerar);
                reference.child(usernameUser).child("numerar").setValue(nou_numerar);
                dif = cheltuiala_vechi + suma_n_add;
                System.out.println(dif);


                String total1 = Convert_string(dif);

                System.out.println("este " + total1);
                if (cheltuiala_vechi != dif) {
                    Toast.makeText(Adaugacheltuieli.this, "aici", Toast.LENGTH_SHORT).show();

                    reference.child(usernameUser).child("cheltuiala").setValue(total1);


                    Toast.makeText(Adaugacheltuieli.this, "aici", Toast.LENGTH_SHORT).show();


                    return true;
                } else {


                    return false;
                }
            } else {
                Toast.makeText(Adaugacheltuieli.this, "Nu mai ai buget", Toast.LENGTH_SHORT).show();
            }

        } else if (tip_data.equals("cont banca")) {
            // System.out.println("veche_suma_banca"+ suma_veche_bn);
            suma_nou_banca = suma_veche_bn - suma_n_add;
            if (suma_nou_banca >= 0) {
                String nou_banca = Convert_string(suma_nou_banca);
                System.out.println(nou_banca);
                reference.child(usernameUser).child("banca").setValue(nou_banca);

                dif = cheltuiala_vechi + suma_n_add;
                System.out.println(dif);


                String total1 = Convert_string(dif);

                System.out.println("este " + total1);
                if (dif != cheltuiala_vechi) {
                    Toast.makeText(Adaugacheltuieli.this, "aici", Toast.LENGTH_SHORT).show();

                    reference.child(usernameUser).child("cheltuiala").setValue(total1);


                    Toast.makeText(Adaugacheltuieli.this, "aici", Toast.LENGTH_SHORT).show();


                    return true;
                } else {


                    return false;
                }
            }
            else {
                Toast.makeText(Adaugacheltuieli.this, "Nu ai buget", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }



    private int Convert(String conv){

            int total = Integer.parseInt(conv);
            Toast.makeText(Adaugacheltuieli.this, "aici", Toast.LENGTH_SHORT).show();
            return total;
    }

    private String Convert_string(int total){
        String total_numerar=String.valueOf(total);
        return total_numerar;
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public Boolean validatedata() {
        String val = data.getText().toString();
        if (val.isEmpty()) {
            data.setError("Data cannot be empty");
            return false;
        } else {
            data.setError(null);
            return true;
        }
    }
    public Boolean validateden() {
        String val = denumire.getText().toString();
        if (val.isEmpty()) {
            denumire.setError("Denumire cannot be empty");
            return false;
        } else {
            denumire.setError(null);
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