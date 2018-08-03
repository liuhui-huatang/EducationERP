package com.htcompany.educationerpforgansu.homepart.entity;

import java.io.Serializable;

/**
 * 奖励实体类
 * Created by wrb on 2017/4/20.
 */
public class MyRewardsEntity implements Serializable{
           private String project;//": " 获奖项目1",
            private String username;//": "小芳",
            private String content;//": "奖励内容\n",
            private String time;//": "2017-01-12",
            private String level;//": "省、部委级",
            private String grade;//": "特等",
            private String category;//": "科技获奖",
            private String manner;//": "实物",
            private String name;//": "奖励名称",
            private String unit;//": "颁奖单位",
            private String honor;//": "基层单位荣誉称号",
            private String role;//": "独立完成人",
            private String cause;//": "获奖原因\nssss"

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManner() {
        return manner;
    }

    public void setManner(String manner) {
        this.manner = manner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
