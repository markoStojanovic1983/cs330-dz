package com.metropolitan.cs330_dz06;

public class ObjectStudent {

    private int broj_indeksa;
    private String ime;
    private int broj_bodova;

    public ObjectStudent(int broj_indeksa, String ime, int broj_bodova) {
        this.broj_indeksa = broj_indeksa;
        this.ime = ime;
        this.broj_bodova = broj_bodova;
    }

    public int getBroj_indeksa() {
        return broj_indeksa;
    }

    public void setBroj_indeksa(int broj_indeksa) {
        this.broj_indeksa = broj_indeksa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getBroj_bodova() {
        return broj_bodova;
    }

    public void setBroj_bodova(int broj_bodova) {
        this.broj_bodova = broj_bodova;
    }


}