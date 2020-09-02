package com.example.conversormoedas.model;

public class Moeda {

    private String name;
    private double buy;
    private double sell;
    private double variation;

    public Moeda() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

}
