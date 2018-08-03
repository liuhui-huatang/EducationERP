package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 班主任考核汇总实体类
 * Created by wrb on 2017/4/13.
 */
public class BZRKHHZEntity implements Serializable{
            private String teacher_name;//": "admin",
            private String name;//": "行政12班-admin",
            private String totalScores;//": 0,
            private String paiming;//": 1,
            private String number;//": "admin"

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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

    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
