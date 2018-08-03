package com.htcompany.educationerpforgansu.workpart.educationalpart.Entity;

import java.io.Serializable;

/**
 * 清考成绩实体类
 * Created by wrb on 2016/12/16.
 */
public class QKCJEntity implements Serializable{
           private String truename;//": "新生19",
            private String number;//": "JZG0111",
            private String jxbname;//": "教学11班",
            private String courceName;//": "PHP基础",
            private String score;//": 60

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getJxbname() {
        return jxbname;
    }

    public void setJxbname(String jxbname) {
        this.jxbname = jxbname;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
