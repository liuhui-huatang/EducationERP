package com.htcompany.educationerpforgansu.workpart.educationalpart.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * 班级成绩实体类
 * Created by wrb on 2016/12/16.
 */
public class ClassCjEntity implements Serializable{
           private String username;//": "杨一一",
            private String number;//": "11",
            private String jxb;//": "教学11班",
            private String total;//": 20,
            private String xufen;//": 0

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String photo;
           private List<ClassCJkcEntity> kc;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getXufen() {
        return xufen;
    }

    public void setXufen(String xufen) {
        this.xufen = xufen;
    }

    public List<ClassCJkcEntity> getKc() {
        return kc;
    }

    public void setKc(List<ClassCJkcEntity> kc) {
        this.kc = kc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getJxb() {
        return jxb;
    }

    public void setJxb(String jxb) {
        this.jxb = jxb;
    }
}
