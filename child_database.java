package com.example.proiect_java;

public class child_database {

    public String getNumerar() {
        return numerar;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumerar(String numerar) {
        this.numerar = numerar;
    }
    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }
    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getVenit(){
        return venit;
    }
    public void setVenit(String venit){
        this.venit=venit;
    }

    public String getBanca(){
        return banca;
    }
    public void setBanca(String banca){
        this.banca=banca;
    }

    public String getCheltuiala() {
        return cheltuiala;
    }
    public void setCheltuiala(String cheltuiala) {
        this.cheltuiala = cheltuiala;
    }


    public child_database(String parola, String nume, String numerar, String venit, String banca, String cheltuiala) {
        this.parola = parola;
        this.nume=nume;
        this.numerar=numerar;
        this.venit=venit;
        this.banca=banca;
        this.cheltuiala=cheltuiala;


    }



    String nume, parola,numerar,venit,banca,cheltuiala;

}
