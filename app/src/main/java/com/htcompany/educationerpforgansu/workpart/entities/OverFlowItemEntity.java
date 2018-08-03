package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 经办人实体类
 * Created by wrb on 2017/4/23.
 */
public class OverFlowItemEntity implements Serializable{
           private String uid;//": "1r3n0qfe-583b9ef47c5d1",
            private String username;//": "testUsername",
            private String org;//": "办公室",
            private String status;//": "未办理",
            private String bltime;//": "",
            private String blyj;//": ""

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBltime() {
        return bltime;
    }

    public void setBltime(String bltime) {
        this.bltime = bltime;
    }

    public String getBlyj() {
        return blyj;
    }

    public void setBlyj(String blyj) {
        this.blyj = blyj;
    }
}
