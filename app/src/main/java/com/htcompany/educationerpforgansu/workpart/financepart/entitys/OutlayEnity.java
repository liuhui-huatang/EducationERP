package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;

/**
 * 支出管理实体列
 * Created by wrb on 2017/4/10.
 */
public class OutlayEnity implements Serializable{
          private String id;
    private String headerImg;
    private String money;//": 22,
    private String type;//": "111",
    private String time;//": "2017-03-09",
    private String teacherName;//": "小新",
    private String number;//": "admin888555",
    private String xueqi;//": "2015~2016学年第一学期",
    private String jigou;//": "办公室",
    private String gangwei;//": "支部书记",
    private String status;//": 1未审核，2审核通过，3审核失败
    private String reason;//": ""
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHeaderImg() {

        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getXueqi() {
        return xueqi;
    }

    public void setXueqi(String xueqi) {
        this.xueqi = xueqi;
    }

    public String getJigou() {
        return jigou;
    }

    public void setJigou(String jigou) {
        this.jigou = jigou;
    }

    public String getGangwei() {
        return gangwei;
    }

    public void setGangwei(String gangwei) {
        this.gangwei = gangwei;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
