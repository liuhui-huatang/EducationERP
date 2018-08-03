package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 班级实体类
 * Created by wrb on 2016/12/19.
 */
public class AllClassEntity implements Serializable{
    private String cname;
    private String cid;
    private String name;//": "行政12班-admin
    private String onecode;//": "1t3vljua-58197a8840d77"
    public String getOnecode() {
        return onecode;
    }

    public void setOnecode(String onecode) {
        this.onecode = onecode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
