package com.laioffer.laiofferproject;

import android.graphics.Bitmap;

import java.util.List;

public class Restaurant {
    /**
     * All data
     */
    private String name;
    private String address;
    private String type;
    private double lat;
    private double lng;
    private Bitmap thumbnail;
    private Bitmap rating;
    private List<String> categories;
    private double stars;
    private String url;




    /**
     * Constructor
     */
    public Restaurant(String name, String address, String type, double lat, double lng, Bitmap thumbnail, Bitmap rating) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.lng = lng;
        this.lat = lat;
        this.thumbnail = thumbnail;
        this.rating = rating;
    }



    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public Bitmap getThumbnail() { return thumbnail; }
    public Bitmap getRating() { return rating; }
    public Restaurant(){}

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {  this.address = address; }
    public void setType(String type) { this.type = type; }
    public void setLat(double lat) { this.lat = lat; }
    public void setLng(double lng) { this.lng = lng; }
    public void setThumbnail(Bitmap thumbnail) { this.thumbnail = thumbnail; }
    public void setRating(Bitmap rating) { this.rating = rating; }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    private String item_id;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }


    private boolean favorate;

    public boolean isFavorate() {
        return favorate;
    }

    public void setFavorate(boolean favorate) {
        this.favorate = favorate;
    }

}

