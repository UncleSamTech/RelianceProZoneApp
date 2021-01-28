package com.reliancehmotest.prozoneapp;

public class ReliancePostResponseModel {
    public String id;
    public String name;
    public String description;
    public int rating;
    public String active_status;
    public Object provider_type;
    public Object state;

    public ReliancePostResponseModel(String id, String name, String description, int rating, String active_status, String provider_type, String state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
    }

    public ReliancePostResponseModel(String id, String name, String description, int rating, String active_status, String provider_type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.active_status = active_status;
        this.provider_type = provider_type;
    }

    public ReliancePostResponseModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public Object getProvider_type() {
        return provider_type;
    }

    public void setProvider_type(String provider_type) {
        this.provider_type = provider_type;
    }

    public Object getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
