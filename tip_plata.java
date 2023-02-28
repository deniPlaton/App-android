package com.example.proiect_java;

public class tip_plata {
    private String tip_de_plata;

    public tip_plata(String tip_de_plata) {
        this.tip_de_plata = tip_de_plata;
    }

    public String getTip_de_plata() {
        return tip_de_plata;
    }

    public void setTip_de_plata(String tip_de_plata) {
        this.tip_de_plata = tip_de_plata;
    }

    @Override
    public String toString() {
        return tip_de_plata;
    }
}
