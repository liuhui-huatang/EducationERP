package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 入住退宿查询实体类
 * Created by wrb on 2017/5/8.
 */
public class DormRzTsSearchEntity implements Serializable{
            private String id;//": "8",
            private String type;//": "入住",
            private String date;//": "2017-01-05",
            private String room_id;//": "19",
            private String content;//": "正常入住",
            private String stuid;//": "q8chlbtu-585a11d299092",
            private String house;//": "332",
            private String stu_name;//": "卢晓",
            private String sex;//": "女",
            private String xzb;//": "行政4班-陈鸿",
            private String teacherName;//": "admin",
            private String imgurl;//": ""

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getXzb() {
        return xzb;
    }

    public void setXzb(String xzb) {
        this.xzb = xzb;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
