package com.example.proiect_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Inregistrare extends AppCompatActivity {
        EditText signupName, signupPasword;
        TextView loginRedirectText;
        Button signupButoon;
        FirebaseDatabase database;
        DatabaseReference reference;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inregistrare);

            signupName=findViewById(R.id.user);
            signupPasword=findViewById(R.id.new_new_parola);
            signupButoon=findViewById(R.id.inregistreazate);
            String zero="0";
            signupButoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database=FirebaseDatabase.getInstance();
                    reference=database.getReference("Users");

                    String nume=signupName.getText().toString();
                    String password=signupPasword.getText().toString();
                    String numerar=zero.toString();
                    String venit=zero.toString();
                    String banca=zero.toString();
                    String cheltuiala=zero.toString();

                    child_database helperClass= new child_databaseBuilder().setParola(password).setNume(nume).setNumerar(numerar).setVenit(venit).setBanca(banca).setCheltuiala(cheltuiala).createHelperclasess();
                    reference.child(nume).setValue(helperClass);





                    Toast.makeText(Inregistrare.this, "You have signup succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(Inregistrare.this, Log_in.class);
                    startActivity(intent);
                }
            });
        }
}
