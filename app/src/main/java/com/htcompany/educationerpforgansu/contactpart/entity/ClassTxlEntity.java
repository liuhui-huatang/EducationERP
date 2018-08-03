package com.htcompany.educationerpforgansu.contactpart.entity;

import java.io.Serializable;

/**
 * 班级通讯录
 * Created by wrb on 2017/1/21.
 */
public class ClassTxlEntity implements Serializable{
            private String truename;//": "卢晓",
            private String uid;//": "q8chlbtu-585a11d299092",
            private String number;//": "112110",
            private String gender;//": "女",
            private String phone;//": "13261538264",
            private String hxuser;//": "sttest1",
            private String hxpassword;//": "123"
            private String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHxuser() {
        return hxuser;
    }

    public void setHxuser(String hxuser) {
        this.hxuser = hxuser;
    }

    public String getHxpassword() {
        return hxpassword;
    }

    public void setHxpassword(String hxpassword) {
        this.hxpassword = hxpassword;
    }
}
