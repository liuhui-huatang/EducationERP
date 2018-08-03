package com.htcompany.educationerpforgansu.workpart.personnelpart.entitys;

import java.io.Serializable;

/**
 * 教职工请假实体
 * Created by wrb on 2017/5/8.
 */
public class PersonLeaveApplyEntity implements Serializable{
           private String id;//": 33,
            private String username;//": "张三",
            private String org;//": "办公室",
            private String start;//": "2017-04-28",
            private String end;//": "2017-04-28",
            private String reason;//": "看见",
            private String status;//": "已提交",
            private String type;//": 1
            private String qj_status;//": 0,已录入，1，已提交，2：审核未通过，3：审核通过，4：已销假
            private String header_img;
            private String typename;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getQj_status() {
        return qj_status;
    }

    public void setQj_status(String qj_status) {
        this.qj_status = qj_status;
    }

    public String getHeader_img() {
        return header_img;
    }
    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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
