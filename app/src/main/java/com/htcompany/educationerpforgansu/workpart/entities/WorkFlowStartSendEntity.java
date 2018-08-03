package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 发起工作流实体类
 * Created by wrb on 2017/4/23.
 */
public class WorkFlowStartSendEntity implements Serializable{
           private String id;//": 33,
            private String name;//": "test",
            private String process_category_id;//": 41,
            private String number_type;//": "{F}",
            private String type;//": 1,
            private String notes;//": "222",
            private String status;//": 1,
            private String form_id;//": 71,
            private String process_name;//": "报销流程",
            private String type_name;//": "固定工作流",
            private String status_name;//": "启用"
            private String yuansheng;//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcess_category_id() {
        return process_category_id;
    }

    public void setProcess_category_id(String process_category_id) {
        this.process_category_id = process_category_id;
    }

    public String getNumber_type() {
        return number_type;
    }

    public void setNumber_type(String number_type) {
        this.number_type = number_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getYuansheng() {
        return yuansheng;
    }

    public void setYuansheng(String yuansheng) {
        this.yuansheng = yuansheng;
    }
}
