package com.example.proiect_java;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
public class Log_in extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton,signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginUsername = findViewById(R.id.user);
        loginPassword = findViewById(R.id.parola);
        loginButton = findViewById(R.id.login);
        signupRedirectText = findViewById(R.id.mai_departe);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) return;
                else {
                    checkUser();
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Inregistrare.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }
    public void checkUser(){

        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query ref=reference.orderByChild("nume").equalTo(userUsername);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    //Toast.makeText(MainActivity.this, "aici ", Toast.LENGTH_LONG).show();
                    loginUsername.setError(null);
                    String password = snapshot.child(userUsername).child("parola").getValue(String.class);

                    if (!Objects.equals(password, userPassword)) {
                                    loginPassword.setError("Invalid Credentials");
                                    loginPassword.requestFocus();
                    } else {
                                    //Toast.makeText(MainActivity.this, "aici corect", Toast.LENGTH_LONG).show();
                                    loginUsername.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("nume").getValue(String.class);
                       // String numerarFromDB = snapshot.child(userUsername).child("numerar").getValue(String.class);
                        //String veniDB=snapshot.child(userUsername).child("venit").getValue(String.class);
                        //String key_bancaDB=snapshot.child(userUsername).child("banca").child("key").getValue(String.class);
                        //System.out.println(numerarFromDB);
                        //String usernameFromDB = snapshot.child(userUsername).child("parola").getValue(String.class);
                       // Intent intent = new Intent(MainActivity.this,Adauganumerar.class);

                       // intent.putExtra("numerarDB", numerarFromDB);
                        //intent.putExtra("numeDDb",nameFromDB);

                        Intent intent1=new Intent(Log_in.this,Meniu_principal.class);
                        //intent1.putExtra("venit_mm",veniDB);
                       // intent1.putExtra("banca_mm",key_bancaDB);
                        intent1.putExtra("numeDB",nameFromDB );
                        //intent1.putExtra("numerar_numerar",numerarFromDB);
                        //intent1.putExtra("i",i);
                        //intent1.putExtra("v",v);
                        startActivity(intent1);


                    }
                } else {
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void Open2(String nume1){


        Intent intent=new Intent(Log_in.this,Meniu_principal.class);
        intent.putExtra("key",nume1 );
        startActivity(intent);
    }
}