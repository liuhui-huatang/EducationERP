package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 课表实体类
 * Created by wrb on 2017/1/20.
 */
public class ClassTableEntity implements Serializable {
            private String name;//": "",
            private String id;//": "",
            private String time;//": " 09:00-09:40",
            private String iscource;//": "",
            private String classname;//": "",
            private String roomname;//": "",
            private String courcename;//": "",
            private  int week;//": 5,int
            private int secid;//": 2
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourcename() {
        return courcename;
    }

    public void setCourcename(String courcename) {
        this.courcename = courcename;
    }

    public String getIscource() {
        return iscource;
    }

    public void setIscource(String iscource) {
        this.iscource = iscource;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSecid() {
        return secid;
    }

    public void setSecid(int secid) {
        this.secid = secid;
    }
}
