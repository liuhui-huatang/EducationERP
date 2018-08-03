package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 人员实体类
 * Created by wrb on 2016/12/13.
 */
public class AllPersonEntity implements Serializable{
    private String pname;//姓名
    private String pid;//id

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
