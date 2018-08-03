package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 学期实体类
 * Created by wrb on 2017/4/17.
 */
public class AllTermsEntity implements Serializable{
            private String label;//": "2015~2016学年第一学期",
            private String value;//": "csvi8fpl-5812c34406cb7",
            private String iscur;//": 0,1但钱学期
            private int weeks;//

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIscur() {
        return iscur;
    }

    public void setIscur(String iscur) {
        this.iscur = iscur;
    }
}
