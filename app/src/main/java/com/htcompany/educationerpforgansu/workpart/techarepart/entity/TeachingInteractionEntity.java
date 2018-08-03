package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 教学互动问题实体类
 * Created by wrb on 2016/12/20.
 */
public class TeachingInteractionEntity implements Serializable{
           private String id;//": "ocl3rt4a-58a266e95aa97",
            private String class_id;//": "lkmsvovc-583bd126851be",
            private String stu_id;//": "q8chlbtu-585a11d299092",
            private String teacher_id;//": "hfljsl0m-584e3b91a48e8",
            private String question;//": "小芳干啥呢？",
            private String kecheng_id;//": "5",
            private String twtime;//": "2017-02-14",
            private String jdtime;//": "",
            private String question_da;//": "",
            private String is_yes;//": 0,
            private String teacher_name;//": "张三",
            private String stu_username;//": "112110",
            private String kecheng;//": "高等数学",
            private String is_yes_ta;//": "已提交"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getKecheng_id() {
        return kecheng_id;
    }

    public void setKecheng_id(String kecheng_id) {
        this.kecheng_id = kecheng_id;
    }

    public String getTwtime() {
        return twtime;
    }

    public void setTwtime(String twtime) {
        this.twtime = twtime;
    }

    public String getJdtime() {
        return jdtime;
    }

    public void setJdtime(String jdtime) {
        this.jdtime = jdtime;
    }

    public String getQuestion_da() {
        return question_da;
    }

    public void setQuestion_da(String question_da) {
        this.question_da = question_da;
    }

    public String getIs_yes() {
        return is_yes;
    }

    public void setIs_yes(String is_yes) {
        this.is_yes = is_yes;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getStu_username() {
        return stu_username;
    }

    public void setStu_username(String stu_username) {
        this.stu_username = stu_username;
    }

    public String getKecheng() {
        return kecheng;
    }

    public void setKecheng(String kecheng) {
        this.kecheng = kecheng;
    }

    public String getIs_yes_ta() {
        return is_yes_ta;
    }

    public void setIs_yes_ta(String is_yes_ta) {
        this.is_yes_ta = is_yes_ta;
    }
}
