package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 学生违纪
 * Created by wrb on 2016/12/22.
 */
public class StudentWJQKEntity implements Serializable{
    private String xq;
    private String xsname;
    private String time;
    private String wjjg;
    private String wjyx;

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getXsname() {
        return xsname;
    }

    public void setXsname(String xsname) {
        this.xsname = xsname;
    }

    public String getWjjg() {
        return wjjg;
    }

    public void setWjjg(String wjjg) {
        this.wjjg = wjjg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWjyx() {
        return wjyx;
    }

    public void setWjyx(String wjyx) {
        this.wjyx = wjyx;
    }
}
