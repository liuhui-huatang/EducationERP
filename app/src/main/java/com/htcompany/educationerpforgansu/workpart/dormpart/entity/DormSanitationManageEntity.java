package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 宿舍检查实体类
 * Created by wrb on 2017/4/26.
 */
public class DormSanitationManageEntity implements Serializable{
            private String id;//": "40",
            private String da_data;//": "2017-06-01",
            private String da_sushe;//": "9",
            private String da_allpoint;//": "12",
            private String sushe_name;//": "1121",
            private String room_floor;//": "11",
            private String sushe_type;//": "小区",
            private String sushe_lou;//": "实验楼2",
    private String sushe_num;

    public String getSushe_num() {
        return sushe_num;
    }

    public void setSushe_num(String sushe_num) {
        this.sushe_num = sushe_num;
    }

    List<DormSanitationManageSourceEntity> OtherField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDa_data() {
        return da_data;
    }

    public void setDa_data(String da_data) {
        this.da_data = da_data;
    }

    public String getDa_sushe() {
        return da_sushe;
    }

    public void setDa_sushe(String da_sushe) {
        this.da_sushe = da_sushe;
    }

    public String getDa_allpoint() {
        return da_allpoint;
    }

    public void setDa_allpoint(String da_allpoint) {
        this.da_allpoint = da_allpoint;
    }

    public String getSushe_name() {
        return sushe_name;
    }

    public void setSushe_name(String sushe_name) {
        this.sushe_name = sushe_name;
    }

    public String getRoom_floor() {
        return room_floor;
    }

    public void setRoom_floor(String room_floor) {
        this.room_floor = room_floor;
    }

    public String getSushe_type() {
        return sushe_type;
    }

    public void setSushe_type(String sushe_type) {
        this.sushe_type = sushe_type;
    }

    public String getSushe_lou() {
        return sushe_lou;
    }

    public void setSushe_lou(String sushe_lou) {
        this.sushe_lou = sushe_lou;
    }

    public List<DormSanitationManageSourceEntity> getOtherField() {
        return OtherField;
    }

    public void setOtherField(List<DormSanitationManageSourceEntity> otherField) {
        OtherField = otherField;
    }
}
