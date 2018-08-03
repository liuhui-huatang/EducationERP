package com.htcompany.educationerpforgansu.workpart.educationalpart.Entity;

import java.io.Serializable;

/**
 * 教师评教实体
 * Created by wrb on 2016/12/19.
 */
public class TeacherPJEntity implements Serializable{
   private String xq;
    private String bpjs;
    private String pjjs;

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getBpjs() {
        return bpjs;
    }

    public void setBpjs(String bpjs) {
        this.bpjs = bpjs;
    }

    public String getPjjs() {
        return pjjs;
    }

    public void setPjjs(String pjjs) {
        this.pjjs = pjjs;
    }
}
