package com.example.seccion_03_recyclerview_card_view.models;

public class Movie {

    private String name;
    private int poster;

    public Movie() {

    }

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return this.poster;
    }
    public void setPoster(int poster) {
        this.poster = poster;
    }
}
