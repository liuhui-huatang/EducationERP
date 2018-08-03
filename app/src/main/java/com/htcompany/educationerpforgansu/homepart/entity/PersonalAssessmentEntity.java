package com.htcompany.educationerpforgansu.homepart.entity;

import java.io.Serializable;

/**
 * 个人考核实体类
 * Created by wrb on 2017/4/20.
 */
public class PersonalAssessmentEntity implements Serializable{
            private String self_score;//": 8,
            private String total_score;//": 11,
            private String theme;//": "考核主题几点"

    public String getSelf_score() {
        return self_score;
    }

    public void setSelf_score(String self_score) {
        this.self_score = self_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
