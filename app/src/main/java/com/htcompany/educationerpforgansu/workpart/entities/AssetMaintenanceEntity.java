package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 资产报修实体类
 * Created by wrb on 2016/12/9.
 */
public class AssetMaintenanceEntity implements Serializable{
    private String id;//": 1,
    private String title;//": "屋顶漏水",
    private String miaoshu;//": "开玩笑",
    private String imgurl;//": null,
    private String treatment_status_id;//": "3",
    private String compensate_statue;//": 1,
    private String show_treatment_status_id;//": "已处理",
    private String show_compensate_statue;//": "未赔偿",
    private String repair_time;//": null
    private String place;//": "帝国大厦",
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTreatment_status_id() {
        return treatment_status_id;
    }

    public void setTreatment_status_id(String treatment_status_id) {
        this.treatment_status_id = treatment_status_id;
    }

    public String getCompensate_statue() {
        return compensate_statue;
    }

    public void setCompensate_statue(String compensate_statue) {
        this.compensate_statue = compensate_statue;
    }

    public String getShow_treatment_status_id() {
        return show_treatment_status_id;
    }

    public void setShow_treatment_status_id(String show_treatment_status_id) {
        this.show_treatment_status_id = show_treatment_status_id;
    }

    public String getShow_compensate_statue() {
        return show_compensate_statue;
    }

    public void setShow_compensate_statue(String show_compensate_statue) {
        this.show_compensate_statue = show_compensate_statue;
    }

    public String getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
    }
}
