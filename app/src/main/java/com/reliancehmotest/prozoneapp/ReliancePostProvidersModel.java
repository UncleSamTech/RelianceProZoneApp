package com.reliancehmotest.prozoneapp;

public class ReliancePostProvidersModel {
    public int id;
    public String name;
    public String description;
    public int rating;
    public String address;
    public String active_status;
    public String provider_type;
    public String state;
    public String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReliancePostProvidersModel(String name, String description, int rating, String address, String active_status, String provider_type, String state) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
    }

    public ReliancePostProvidersModel(int id, String name, String description, int rating, String address, String active_status, String provider_type, String state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
    }

    public ReliancePostProvidersModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getProvider_type() {
        return provider_type;
    }

    public void setProvider_type(String provider_type) {
        this.provider_type = provider_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
