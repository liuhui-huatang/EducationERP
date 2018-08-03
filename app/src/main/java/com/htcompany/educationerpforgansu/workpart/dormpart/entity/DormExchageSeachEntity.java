package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 宿舍调换查询实体类
 * Created by wrb on 2017/5/8.
 */
public class DormExchageSeachEntity implements Serializable{
           private String id;//": "3",
            private String stuid;//": "o49cjqnb-586cd190beb6e",
            private String room_id_change;//": "sdf ",
            private String room_id_before;//": "7",
            private String bed_before;//": "321",
            private String bed_change;//": "321",
            private String date;//": "2017-01-05",
            private String content;//": "",
            private String stu_name;//": "test",
            private String sex;//": "男",
            private String xzb;//": "行政4班-陈鸿",
            private String teacherName;//": "admin",
            private String room_before;//": "sdf ",
            private String imgurl;//": ""

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getRoom_id_change() {
        return room_id_change;
    }

    public void setRoom_id_change(String room_id_change) {
        this.room_id_change = room_id_change;
    }

    public String getRoom_id_before() {
        return room_id_before;
    }

    public void setRoom_id_before(String room_id_before) {
        this.room_id_before = room_id_before;
    }

    public String getBed_before() {
        return bed_before;
    }

    public void setBed_before(String bed_before) {
        this.bed_before = bed_before;
    }

    public String getBed_change() {
        return bed_change;
    }

    public void setBed_change(String bed_change) {
        this.bed_change = bed_change;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getRoom_before() {
        return room_before;
    }

    public void setRoom_before(String room_before) {
        this.room_before = room_before;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
