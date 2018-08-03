package com.htcompany.educationerpforgansu.workpart.commonworkpart.entity;

import java.io.Serializable;

/**
 * 车辆使用实体类
 * Created by wrb on 2016/12/14.
 */
public class WorkCarEntity implements Serializable{
           private String id;//": "15",
            private String name;//": "宝马汽车",
            private String number;//": "资产编号",
            private String applyer;//": "admin",
            private String license_plate_number;//": "冀A·QA7898",
            private String uc_status;//": "未通过申请",
            private String uc_end_place;//": "bhj",
            private String uc_start_time;//": "2017-04-24",
            private String uc_end_time;//": "2017-04-24 00:00:00",
            private String uc_mileage;//": "0",
            private String uc_resion;//": "gbh",
            private String auditer;//": "admin",
            private String uc_audit;//": "attgq7ee-583b9a599e11c",
            private String uc_audit_time;//": "2017-04-24 20:54:07",
            private String uc_audit_resion;//": "不通过的理由",
            private String uc_driver;//": "ng9lfg7p-583ff5dc59054",
            private String username;//": "xueshengke"
            private String uc_status_key;//0已录入，1已申请，2通过审核，3已归还，4.已取消，5未通过申请，6已结束
            private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUc_status_key() {
        return uc_status_key;
    }

    public void setUc_status_key(String uc_status_key) {
        this.uc_status_key = uc_status_key;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getLicense_plate_number() {
        return license_plate_number;
    }

    public void setLicense_plate_number(String license_plate_number) {
        this.license_plate_number = license_plate_number;
    }

    public String getUc_status() {
        return uc_status;
    }

    public void setUc_status(String uc_status) {
        this.uc_status = uc_status;
    }

    public String getUc_end_place() {
        return uc_end_place;
    }

    public void setUc_end_place(String uc_end_place) {
        this.uc_end_place = uc_end_place;
    }

    public String getUc_start_time() {
        return uc_start_time;
    }

    public void setUc_start_time(String uc_start_time) {
        this.uc_start_time = uc_start_time;
    }

    public String getUc_end_time() {
        return uc_end_time;
    }

    public void setUc_end_time(String uc_end_time) {
        this.uc_end_time = uc_end_time;
    }

    public String getUc_mileage() {
        return uc_mileage;
    }

    public void setUc_mileage(String uc_mileage) {
        this.uc_mileage = uc_mileage;
    }

    public String getUc_resion() {
        return uc_resion;
    }

    public void setUc_resion(String uc_resion) {
        this.uc_resion = uc_resion;
    }

    public String getAuditer() {
        return auditer;
    }

    public void setAuditer(String auditer) {
        this.auditer = auditer;
    }

    public String getUc_audit() {
        return uc_audit;
    }

    public void setUc_audit(String uc_audit) {
        this.uc_audit = uc_audit;
    }

    public String getUc_audit_time() {
        return uc_audit_time;
    }

    public void setUc_audit_time(String uc_audit_time) {
        this.uc_audit_time = uc_audit_time;
    }

    public String getUc_audit_resion() {
        return uc_audit_resion;
    }

    public void setUc_audit_resion(String uc_audit_resion) {
        this.uc_audit_resion = uc_audit_resion;
    }

    public String getUc_driver() {
        return uc_driver;
    }

    public void setUc_driver(String uc_driver) {
        this.uc_driver = uc_driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
