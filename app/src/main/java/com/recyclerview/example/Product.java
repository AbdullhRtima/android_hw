package com.recyclerview.example;

import java.util.ArrayList;

/**
 * Created by beliv on 2/28/2018.
 */

public class Product {
    private int id;
    private String name;
    private boolean isFavorite;

    public Product(int id, String name, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}