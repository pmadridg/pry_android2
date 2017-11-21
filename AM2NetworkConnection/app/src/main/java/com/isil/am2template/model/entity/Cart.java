package com.isil.am2template.model.entity;

/**
 * Created by Pablo Claus on 11/10/2017.
 */

public class Cart {
    private String id;
    private String title;
    private String qty;

    public Cart() {}

    public Cart(String id, String title, String qty) {
        this.id = id;
        this.title = title;
        this.qty = qty;
    }

    public Cart(String title, String qty) {
        this.title = title;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
