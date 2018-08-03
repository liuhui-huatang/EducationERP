package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 教师培训实体
 * Created by wrb on 2016/12/19.
 */
public class TeacherPXEntity implements Serializable{
           private String id;//": 17,
            private String jiashi_id;//": 14,
            private String pei_name;//": "32",
            private String pei_xuhao;//": "32",
            private String pei_jidi;//": "32",
            private String pei_num;//": 32,
            private String pei_atime;//": "2017-05-05",
            private String pei_btime;//": "2017-05-12",
            private String pei_status;//": "1",
            private String pei_danwei;//": "32",
            private String pei_con;//": "32",
            private String name;//": "32",
            private String code;//": "32",
            private String type;//": "32",
            private String pei_status_a;//": "可以报名",
            private String baoming_status;//": "已经报名",
            private String baoming_time;//": "2017-05-05",
            private String baoming_sta;//": 1可以报名，2通过，3不通过

    public String getBaoming_status() {
        return baoming_status;
    }

    public void setBaoming_status(String baoming_status) {
        this.baoming_status = baoming_status;
    }

    public String getBaoming_time() {
        return baoming_time;
    }

    public void setBaoming_time(String baoming_time) {
        this.baoming_time = baoming_time;
    }

    public String getBaoming_sta() {
        return baoming_sta;
    }

    public void setBaoming_sta(String baoming_sta) {
        this.baoming_sta = baoming_sta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJiashi_id() {
        return jiashi_id;
    }

    public void setJiashi_id(String jiashi_id) {
        this.jiashi_id = jiashi_id;
    }

    public String getPei_name() {
        return pei_name;
    }

    public void setPei_name(String pei_name) {
        this.pei_name = pei_name;
    }

    public String getPei_xuhao() {
        return pei_xuhao;
    }

    public void setPei_xuhao(String pei_xuhao) {
        this.pei_xuhao = pei_xuhao;
    }

    public String getPei_jidi() {
        return pei_jidi;
    }

    public void setPei_jidi(String pei_jidi) {
        this.pei_jidi = pei_jidi;
    }

    public String getPei_num() {
        return pei_num;
    }

    public void setPei_num(String pei_num) {
        this.pei_num = pei_num;
    }

    public String getPei_atime() {
        return pei_atime;
    }

    public void setPei_atime(String pei_atime) {
        this.pei_atime = pei_atime;
    }

    public String getPei_btime() {
        return pei_btime;
    }

    public void setPei_btime(String pei_btime) {
        this.pei_btime = pei_btime;
    }

    public String getPei_status() {
        return pei_status;
    }

    public void setPei_status(String pei_status) {
        this.pei_status = pei_status;
    }

    public String getPei_danwei() {
        return pei_danwei;
    }

    public void setPei_danwei(String pei_danwei) {
        this.pei_danwei = pei_danwei;
    }

    public String getPei_con() {
        return pei_con;
    }

    public void setPei_con(String pei_con) {
        this.pei_con = pei_con;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getPei_status_a() {
        return pei_status_a;
    }

    public void setPei_status_a(String pei_status_a) {
        this.pei_status_a = pei_status_a;
    }
}
