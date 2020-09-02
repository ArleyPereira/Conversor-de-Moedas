package com.example.conversormoedas.model;

import java.util.ArrayList;
import java.util.List;

public class MoedaList {

    private String source;
    private Moeda USD;
    private Moeda EUR;
    private Moeda GBP;
    private Moeda ARS;
    private Moeda BTC;
    private List<Moeda> moedaList = new ArrayList<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Moeda getUSD() {
        return USD;
    }

    public void setUSD(Moeda USD) {
        this.USD = USD;
    }

    public Moeda getEUR() {
        return EUR;
    }

    public void setEUR(Moeda EUR) {
        this.EUR = EUR;
    }

    public Moeda getGBP() {
        return GBP;
    }

    public void setGBP(Moeda GBP) {
        this.GBP = GBP;
    }

    public Moeda getARS() {
        return ARS;
    }

    public void setARS(Moeda ARS) {
        this.ARS = ARS;
    }

    public Moeda getBTC() {
        return BTC;
    }

    public void setBTC(Moeda BTC) {
        this.BTC = BTC;
    }

    public List<Moeda> getLista(){
        moedaList.add(getUSD());
        moedaList.add(getEUR());
        moedaList.add(getGBP());
        moedaList.add(getARS());
        moedaList.add(getBTC());
        return moedaList;
    }

}
