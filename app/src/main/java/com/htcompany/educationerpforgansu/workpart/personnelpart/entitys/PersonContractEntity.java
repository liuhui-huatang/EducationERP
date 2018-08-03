package com.htcompany.educationerpforgansu.workpart.personnelpart.entitys;

import java.io.Serializable;

/**
 * 劳动合同实体
 * Created by wrb on 2017/5/6.
 */
public class PersonContractEntity implements Serializable{
           private String id;//": 2,
            private String orgName;//": "办公室",
            private String sta_name;//": "招办副主任",
            private String username;//": "张三",
            private String worker_number;//": "DJ小五",
            private String number;//": "qwe",
            private String start;//": "2017-04-19",
            private String end;//": "2017-04-05",
            private String time;//": "2017-04-19",
            private String status;//": 2,
            private String xuqian_cishu;//": 1,
            private String xuqian_time;//": "2017-04-04",
            private String zhongzhi_reason;//": "",
            private String position;//": "12",
            private String bian_cishu;//": 1,
            private String bian_time;//": "2017-04-04",
            private String bian_reason;//": ""
            private String header_img;

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

    public String getBian_reason() {
        return bian_reason;
    }

    public void setBian_reason(String bian_reason) {
        this.bian_reason = bian_reason;
    }
}
