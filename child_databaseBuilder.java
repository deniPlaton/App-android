package com.example.proiect_java;

public class child_databaseBuilder {
    private String parola;
    private String nume;
    private String numerar;
    private String venit;
    private String banca;
    private String cheltuiala;


    public child_databaseBuilder setParola(String parola) {
        this.parola = parola;
        return this;
    }

    public child_databaseBuilder setNume(String nume) {
        this.nume = nume;
        return this;
    }

    public child_databaseBuilder setNumerar(String numerar) {
        this.numerar = numerar;
        return this;
    }

    public child_databaseBuilder setVenit(String venit) {
        this.venit = venit;
        return this;
    }
    public child_databaseBuilder setBanca(String banca) {
        this.banca= banca;
        return this;
    }
    public child_databaseBuilder setCheltuiala(String cheltuiala){
        this.cheltuiala=cheltuiala;
        return this;
    }

    public child_database createHelperclasess() {
        return new child_database(parola, nume, numerar, venit,banca,cheltuiala);
    }
}