package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 我的请假实体类
 * Created by wrb on 2016/12/9.
 */
public class MyLeaveApplyEntity implements Serializable{
            private String username;//": "testUsername",
            private String org;//": "办公室",
            private String start;//": "2017-04-20",
            private String end;//": "2017-05-04",
            private String reason;//": "13",
            private String status;//": "已录入",
            private String type;//": 1
            private String typename;
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
