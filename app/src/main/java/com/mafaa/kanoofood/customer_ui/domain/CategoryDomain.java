package com.mafaa.kanoofood.customer_ui.domain;

public class CategoryDomain {
    private final String pic;
    private String title;

    public CategoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
