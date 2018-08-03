package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 车辆类型实体类
 * Created by wrb on 2016/12/16.
 */
public class AllCarsTypeEntity implements Serializable{
    private String carname;
    private String carid;
    private String name;//": "宝马汽车",
    private String number;//": "资产编号",
    private String value;//": "124"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }
}
