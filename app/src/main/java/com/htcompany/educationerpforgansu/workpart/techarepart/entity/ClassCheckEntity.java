package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 班级考核实体类
 * Created by wrb on 2016/12/21.
 */
public class ClassCheckEntity implements Serializable{
           private String jingsai;//": " 班级考核主题1",
           private String name;//": "行政2班-张三",
           private String totalScores;//": 0,
           private String sort;//": 3,
           private String teacher_name;//": "DJ小五"

    public String getJingsai() {
        return jingsai;
    }

    public void setJingsai(String jingsai) {
        this.jingsai = jingsai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(String totalScores) {
        this.totalScores = totalScores;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
