package com.reliancehmotest.prozoneapp;

public class RelianceProZoneAppModelClass {
    /*public String img_prov;
    public String prov_name;
    public String prov_loc;
    public String prov_id;*/
    public int id;
    public String name;
    public String description;
    public int rating;
    public String address;
    public String active_status;
    public Object provider_type;
    public Object images [];
    public Object state;

    public Object[] getImages() {
        return images;
    }

    public RelianceProZoneAppModelClass(Object[] images) {
        this.images = images;
    }

    public Object getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public RelianceProZoneAppModelClass(int id, String name, String description, int rating, String address, String active_status, String provider_type,  String state, Object[] images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
    }

    public RelianceProZoneAppModelClass() {
    }

    public RelianceProZoneAppModelClass(  Object[] images,String name,String address) {
        this.name = name;
        this.address = address;

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

    public Object getProvider_type() {
        return provider_type;
    }

    public void setProvider_type(String provider_type) {
        this.provider_type = provider_type;
    }



    public RelianceProZoneAppModelClass(int id, String name, String description, int rating, String address, String active_status, String provider_type, Object[] images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.images = images;

    }

    public RelianceProZoneAppModelClass(int id, String name, String description, int rating, String address, String active_status, String provider_type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
    }

    public RelianceProZoneAppModelClass(String images, String name, String address, int id) {
        this.id = id;
        this.name = name;
        this.address = address;

    }

    public RelianceProZoneAppModelClass(String name, String description, int rating, String address, String active_status, String provider_type, String state) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.active_status = active_status;
        this.provider_type = provider_type;
        this.state = state;
    }

    /*public RelianceProZoneAppModelClass(String img_prov, String prov_name, String prov_loc) {
        this.img_prov = img_prov;
        this.prov_name = prov_name;
        this.prov_loc = prov_loc;
    }

    public RelianceProZoneAppModelClass(String img_prov, String prov_name, String prov_loc, String prov_id) {
        this.img_prov = img_prov;
        this.prov_name = prov_name;
        this.prov_loc = prov_loc;
        this.prov_id = prov_id;
    }

    public String getImg_prov() {
        return img_prov;
    }

    public void setImg_prov(String img_prov) {
        this.img_prov = img_prov;
    }

    public String getProv_name() {
        return prov_name;
    }

    public void setProv_name(String prov_name) {
        this.prov_name = prov_name;
    }

    public String getProv_loc() {
        return prov_loc;
    }

    public void setProv_loc(String prov_loc) {
        this.prov_loc = prov_loc;
    }

    public String getProv_id() {
        return prov_id;
    }

    public void setProv_id(String prov_id) {
        this.prov_id = prov_id;
    }*/
}
