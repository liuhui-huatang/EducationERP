package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 周次实体类
 * Created by wrb on 2017/1/20.
 */
public class WeeksEntity implements Serializable{
    private String label;//第18周",
           private String value;//: 18

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
