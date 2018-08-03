package com.htcompany.educationerpforgansu.workpart.personnelpart.entitys;

import java.io.Serializable;

/**
 * 社会保险实体
 * Created by wrb on 2017/5/6.
 */
public class PersonSocialInsuranceEntity implements Serializable{
           private String id;//": 4,
            private String username;//": "小芳",
            private String teacher_id;//": "6vf2ece1-584f985b2ab20",
            private String worker_number;//": "zhaojiuban",
            private String sex;//": "女",
            private String si_number;//": "社保号",
            private String si_start_time;//": "2017-05-18",
            private String si_end_time;//": "2017-05-26",
            private String si_base;//": "1.00",
            private String si_com_pay;//": "123.00",
            private String si_per_pay;//": "456.00",
            private String si_total;//": "777.00",
            private String document_number;//": "234435223423",
            private String dictName;//": null,
            private String officeName;//": "在职",
            private String orgname;//": "办公室",
            private String sta_name;//": "教师"
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getWorker_number() {
        return worker_number;
    }

    public void setWorker_number(String worker_number) {
        this.worker_number = worker_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSi_number() {
        return si_number;
    }

    public void setSi_number(String si_number) {
        this.si_number = si_number;
    }

    public String getSi_start_time() {
        return si_start_time;
    }

    public void setSi_start_time(String si_start_time) {
        this.si_start_time = si_start_time;
    }

    public String getSi_end_time() {
        return si_end_time;
    }

    public void setSi_end_time(String si_end_time) {
        this.si_end_time = si_end_time;
    }

    public String getSi_base() {
        return si_base;
    }

    public void setSi_base(String si_base) {
        this.si_base = si_base;
    }

    public String getSi_com_pay() {
        return si_com_pay;
    }

    public void setSi_com_pay(String si_com_pay) {
        this.si_com_pay = si_com_pay;
    }

    public String getSi_per_pay() {
        return si_per_pay;
    }

    public void setSi_per_pay(String si_per_pay) {
        this.si_per_pay = si_per_pay;
    }

    public String getSi_total() {
        return si_total;
    }

    public void setSi_total(String si_total) {
        this.si_total = si_total;
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getSta_name() {
        return sta_name;
    }

    public void setSta_name(String sta_name) {
        this.sta_name = sta_name;
    }
}
