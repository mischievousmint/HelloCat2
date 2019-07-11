package com.example.hellocat2;

public class CatModel {

    private String title;
    private String image;
    private Double lat;
    private Double lon;

    public CatModel() {
    }

    public CatModel(String title, String image, Double lat, Double lon) {
        this.title = title;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}

