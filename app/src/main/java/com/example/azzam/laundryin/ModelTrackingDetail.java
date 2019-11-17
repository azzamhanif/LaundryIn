package com.example.azzam.laundryin;

/**
 * Created by azzam on 10/28/2019.
 */

public class ModelTrackingDetail {
    private String status;
    private String desc;

    public ModelTrackingDetail(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public ModelTrackingDetail() {
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
