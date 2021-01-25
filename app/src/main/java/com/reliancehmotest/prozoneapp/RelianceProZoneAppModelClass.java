package com.reliancehmotest.prozoneapp;

public class RelianceProZoneAppModelClass {
    public String img_prov;
    public String prov_name;
    public String prov_loc;
    public String prov_id;

    public RelianceProZoneAppModelClass(String img_prov, String prov_name, String prov_loc) {
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
    }
}
