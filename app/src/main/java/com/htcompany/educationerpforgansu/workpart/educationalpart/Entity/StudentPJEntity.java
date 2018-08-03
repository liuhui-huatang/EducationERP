package com.htcompany.educationerpforgansu.workpart.educationalpart.Entity;

import java.io.Serializable;

/**
 * 学生评教实体类
 * Created by wrb on 2016/12/19.
 */
public class StudentPJEntity implements Serializable{
    private String pjxq;
    private String wjbt;
    private String starttime;
    private String endtime;
    private String fzdw;
    private String zsmcjjb;

    public String getPjxq() {
        return pjxq;
    }

    public void setPjxq(String pjxq) {
        this.pjxq = pjxq;
    }

    public String getWjbt() {
        return wjbt;
    }

    public void setWjbt(String wjbt) {
        this.wjbt = wjbt;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFzdw() {
        return fzdw;
    }

    public void setFzdw(String fzdw) {
        this.fzdw = fzdw;
    }

    public String getZsmcjjb() {
        return zsmcjjb;
    }

    public void setZsmcjjb(String zsmcjjb) {
        this.zsmcjjb = zsmcjjb;
    }
}
