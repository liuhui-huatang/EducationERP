package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 差勤管理实体类
 * Created by wrb on 2017/4/23.
 */
public class DormKnowingManageEntity implements Serializable{
            private String id;//": "18",
            private String date;//": "2017-04-10",
            private String stuid;//": "lzf",
            private String photo;//": "",
            private String build;//": "实验楼2",
            private String roomname;//": "1121",
            private String roomNum;//": "",
            private String bed;//": "",
            private String house_type;//": "",
            private String xzb;//": "行政12班-admin",
            private String teacher;//": "admin",
            private String absenteeism_category_id;//": "13",
            private String show_absenteeism_category_id;//": "病假",
            private String return_time;//": "2017-04-19 11:55:08",
            private String check_chamber_user;//": "321dd"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getXzb() {
        return xzb;
    }

    public void setXzb(String xzb) {
        this.xzb = xzb;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAbsenteeism_category_id() {
        return absenteeism_category_id;
    }

    public void setAbsenteeism_category_id(String absenteeism_category_id) {
        this.absenteeism_category_id = absenteeism_category_id;
    }

    public String getShow_absenteeism_category_id() {
        return show_absenteeism_category_id;
    }

    public void setShow_absenteeism_category_id(String show_absenteeism_category_id) {
        this.show_absenteeism_category_id = show_absenteeism_category_id;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public String getCheck_chamber_user() {
        return check_chamber_user;
    }

    public void setCheck_chamber_user(String check_chamber_user) {
        this.check_chamber_user = check_chamber_user;
    }
}
