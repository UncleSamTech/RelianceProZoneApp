package com.reliancehmotest.prozoneapp;

import java.lang.reflect.Array;

public class RelianceUploadFilesModel {

    public String ref;
    public String refId;
    public String field;
    public Array arrImg [];



    public RelianceUploadFilesModel() {

    }

    public RelianceUploadFilesModel(String ref, String refId, String field, Array[] arrImg) {
        this.ref = ref;
        this.refId = refId;
        this.field = field;
        this.arrImg = arrImg;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Array[] getArrImg() {
        return arrImg;
    }

    public void setArrImg(Array[] arrImg) {
        this.arrImg = arrImg;
    }
}
