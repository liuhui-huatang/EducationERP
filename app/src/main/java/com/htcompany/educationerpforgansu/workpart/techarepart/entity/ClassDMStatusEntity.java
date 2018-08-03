package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 点名选项实体类
 * Created by wrb on 2017/1/22.
 */
public class ClassDMStatusEntity implements Serializable{
        private String name;//": "迟到",
        private String status;//": 1
            private String kpi_id;//": 12,

    public String getKpi_id() {
        return kpi_id;
    }

    public void setKpi_id(String kpi_id) {
        this.kpi_id = kpi_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
