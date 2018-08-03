package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 德育新闻实体类
 * Created by wrb on 2017/2/16.
 */
public class DYNewsEntity implements Serializable{
           private String id;//": 2,
            private String unkey;//": null,
            private String title;//": "test11",
            private String school_date;//": "2015~2016学年第一学期",
            private String author;//": "刘家乐",
            private String update_time;//": "",
            private String orderlist;//": 2,
            private String content;//": "11111",
            private String clicknum;//": 0,
            private String status;//": 2
    private String image;//": "/upload/20170526/592780a8a14381495761064.jpg"


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnkey() {
        return unkey;
    }

    public void setUnkey(String unkey) {
        this.unkey = unkey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(String orderlist) {
        this.orderlist = orderlist;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClicknum() {
        return clicknum;
    }

    public void setClicknum(String clicknum) {
        this.clicknum = clicknum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
