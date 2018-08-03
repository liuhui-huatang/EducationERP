package com.htcompany.educationerpforgansu.workpart.educationalpart.Entity;

import java.io.Serializable;

/**
 * 报名人
 * Created by wrb on 2017/5/6.
 */
public class EducationTeacherTrainingPersonEntity implements Serializable{
            private String id;//": 20,
            private String xiangmu_id;//": 16,
            private String uid;//": "hfljsl0m-584e3b91a48e8",
            private String time;//": "2017-05-05",
            private String status;//": 1,
            private String user_name;//": "张三",
            private String work_number;//": "DJ小五",
            private String pei_name;//": "32",
            private String caozuoa;//": "报名通过",
            private String caozuob;//": "报名退回",
            private String status_table;//": "已经报名"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXiangmu_id() {
        return xiangmu_id;
    }

    public void setXiangmu_id(String xiangmu_id) {
        this.xiangmu_id = xiangmu_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWork_number() {
        return work_number;
    }

    public void setWork_number(String work_number) {
        this.work_number = work_number;
    }

    public String getPei_name() {
        return pei_name;
    }

    public void setPei_name(String pei_name) {
        this.pei_name = pei_name;
    }

    public String getCaozuoa() {
        return caozuoa;
    }

    public void setCaozuoa(String caozuoa) {
        this.caozuoa = caozuoa;
    }

    public String getCaozuob() {
        return caozuob;
    }

    public void setCaozuob(String caozuob) {
        this.caozuob = caozuob;
    }

    public String getStatus_table() {
        return status_table;
    }

    public void setStatus_table(String status_table) {
        this.status_table = status_table;
    }
}
