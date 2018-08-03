package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 宿舍管理
 * Created by wrb on 2017/4/20.
 */
public class TeacherDormManageEntity implements Serializable{
           private String id;//": 6,
            private String number;//": "11",
            private String truename;//": "杨一一",
            private String gender;//": "男",
            private String bed;//": "4",
            private String room_category_id;//": "sky",
            private String num;//": "1#101",
            private String photo;//": null,
            private String is_housemaster;//": "否",
            private String house_name;//": "房间一",
            private String house_type;//": ""

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(String room_category_id) {
        this.room_category_id = room_category_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIs_housemaster() {
        return is_housemaster;
    }

    public void setIs_housemaster(String is_housemaster) {
        this.is_housemaster = is_housemaster;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }
}
