package com.mafaa.kanoofood.customer_ui.domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String pict;
    private String description;
    private Double fee;
    private int numberInCard;

    public FoodDomain(String title, String pict, String description, Double fee) {
        this.title = title;
        this.pict = pict;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomain(String title, String pict, String description, Double fee, int numberInCard) {
        this.title = title;
        this.pict = pict;
        this.description = description;
        this.fee = fee;
        this.numberInCard = numberInCard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
