package com.example.demo;

public class Pazymiai {

    private String vartotojoVardas;
    private String dalykas;
    private Double pazymys;

    public Pazymiai(String vartotojoVardas, Double pazymys) {
        this.vartotojoVardas = vartotojoVardas;
        this.dalykas = dalykas;
        this.pazymys = pazymys;
    }

    public Pazymiai(double destomasDalykas, String pazymys, String vartotojoId) {
    }

    public String getVartotojoVardas() {
        return vartotojoVardas;
    }

    public void setVartotojoVardas(String vartotojoVardas) {
        this.vartotojoVardas = vartotojoVardas;
    }

    public String getDalykas() {
        return dalykas;
    }

    public void setDalykas(String dalykas) {
        this.dalykas = dalykas;
    }

    public Double getPazymys() {
        return pazymys;
    }

    public void setPazymys(Double pazymys) {
        this.pazymys = pazymys;
    }
}