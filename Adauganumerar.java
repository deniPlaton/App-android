package com.example.proiect_java;

import static android.text.TextUtils.*;
import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;
import static java.lang.Math.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.grpc.internal.DnsNameResolver;

public class Adauganumerar extends AppCompatActivity {
    EditText cash;
    Button saveButton,Anuleaza;
    String  usernameUser;
    String numerar,venit_initial;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Istoric");
   // Query ref=reference.orderByChild("nume").equalTo(usernameUser);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauganumerar);

        venit_initial=getIntent().getStringExtra("venit_initial");
        usernameUser=getIntent().getStringExtra("nume_numeral");
        System.out.println(usernameUser);

        //System.out.println(numerar);



        Anuleaza=findViewById(R.id.anuleaza);
        cash =(EditText) findViewById(R.id.cash);
        saveButton = findViewById(R.id.cash_plus);
        //int suma_veche= Convert(numerar);
       // System.out.println(suma_veche);


        numerar = getIntent().getStringExtra("numerar_add");
        int suma_veche=Convert(numerar);
        System.out.println(suma_veche);
        //System.out.println("Continuing execution...");
                        Anuleaza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Adauganumerar.this, Add_buget.class);
                                intent.putExtra("numeDB",usernameUser);
                                startActivity(intent);
                            }
                        });

                        saveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!valdareNumera()){
                                String suma = cash.getText().toString();
                                //String numerar=getIntent().getStringExtra("numerar1");
                                //System.out.println(numerar);

                                    int suma_plus = Convert(suma);
                                    System.out.println(suma_plus);
                                int suma_veche=Convert(numerar);
                                System.out.println(suma_veche);
                                int venit_vechi=Convert(venit_initial);
                                System.out.println(venit_vechi);

                                    if (isNumerarChanged(usernameUser, suma_veche, suma_plus,venit_vechi)) {
                                        Toast.makeText(Adauganumerar.this, "Saved", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Adauganumerar.this, Meniu_principal.class);
                                        intent.putExtra("numeDB",usernameUser);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Adauganumerar.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                                    }


                            }}
                        });
                    }
                    private boolean isNumerarChanged (String usernameUser,int suma_v, int suma_n,int venit_vechi) {
                        int total = 0;
                        total = suma_v + suma_n;
                        System.out.println(total);
                        int total_venit;
                        total_venit=suma_v + venit_vechi;
                        String total_venit_nou = Convert_string(total);
                        String total1 = Convert_string(total);
                       // Toast.makeText(Adauganumerar.this, "aici", Toast.LENGTH_SHORT).show();
                        System.out.println("este " + total1);
                        if (suma_v != total) {
                            reference.child(usernameUser).child("numerar").setValue(total1);
                            reference.child(usernameUser).child("venit").setValue(total_venit_nou);

                            //reference.child("Istoric").child(usernameUser).child("numerar_add").setValue(total1);}
                            Toast.makeText(Adauganumerar.this, "aici", Toast.LENGTH_SHORT).show();
                            //numerarUser = cash.getText().toString();

                            return true;
                        } else {


                            return false;
                        }
                    }

                    private int Convert(String conv){

                        try {
                            int total = Integer.parseInt(conv);
                            Toast.makeText(Adauganumerar.this, "aici", Toast.LENGTH_SHORT).show();
                            return total;
                        }catch (NumberFormatException e){
                            return 0;
                        }
                    }
    private String Convert_string(int total){
        String total_numerar=String.valueOf(total);
        return total_numerar;
    }
    public Boolean valdareNumera() {
        String val = cash.getText().toString();
        if (val.isEmpty()) {
            cash.setError("Denumire cannot be empty");
            return false;
        } else {
            cash.setError(null);
            return true;
        }
    }

}
//offcial gata