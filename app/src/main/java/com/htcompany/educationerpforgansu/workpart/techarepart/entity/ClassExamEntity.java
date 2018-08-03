package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 班级成绩实体类
 * Created by wrb on 2017/4/20.
 */
public class ClassExamEntity implements Serializable{
    private String username;//": "杨一一",
    private String number;//": "11",
    private String jxb;//": "教学11班",
    private String total;//": 20,
    private String xufen;//": 0
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private List<ClassExamKcEntity> kc;

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

    public List<ClassExamKcEntity> getKc() {
        return kc;
    }

    public void setKc(List<ClassExamKcEntity> kc) {
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
