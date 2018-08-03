package com.htcompany.educationerpforgansu.homepart.entity;

import java.io.Serializable;

/**
 * 合同详情
 * Created by wrb on 2017/4/20.
 */
public class ContractDetailsEntity implements Serializable{
           private String orgName;//": "学生科",
            private String sta_name;//": "学生科科长",
            private String username;//": "xueshengke",
            private String worker_number;//": "xueshengke",
            private String number;//": "number",
            private String start;//": "1970-01-02",
            private String end;//": "1970-01-02",
            private String time;//": "1970-09-29",
            private String status;//": 3,
            private String xuqian_cishu;//": 1,
            private String xuqian_time;//": "1970-01-01",
            private String zhongzhi_reason;//": "驱蚊器",
            private String position;//": "",
            private String bian_cishu;//": 1,
            private String bian_time;//": "1970-01-01",

    public String getBian_reason() {
        return bian_reason;
    }

    public void setBian_reason(String bian_reason) {
        this.bian_reason = bian_reason;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSta_name() {
        return sta_name;
    }

    public void setSta_name(String sta_name) {
        this.sta_name = sta_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWorker_number() {
        return worker_number;
    }

    public void setWorker_number(String worker_number) {
        this.worker_number = worker_number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getXuqian_cishu() {
        return xuqian_cishu;
    }

    public void setXuqian_cishu(String xuqian_cishu) {
        this.xuqian_cishu = xuqian_cishu;
    }

    public String getXuqian_time() {
        return xuqian_time;
    }

    public void setXuqian_time(String xuqian_time) {
        this.xuqian_time = xuqian_time;
    }

    public String getZhongzhi_reason() {
        return zhongzhi_reason;
    }

    public void setZhongzhi_reason(String zhongzhi_reason) {
        this.zhongzhi_reason = zhongzhi_reason;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBian_cishu() {
        return bian_cishu;
    }

    public void setBian_cishu(String bian_cishu) {
        this.bian_cishu = bian_cishu;
    }

    public String getBian_time() {
        return bian_time;
    }

    public void setBian_time(String bian_time) {
        this.bian_time = bian_time;
    }

    private String bian_reason;//": ""
}
