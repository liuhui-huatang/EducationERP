package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 宿舍缺勤类别实体
 * Created by wrb on 2017/4/24.
 */
public class DormQQLBEntity implements Serializable{
            private String value;//": "15",
            private String label;//": "产假"

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
