package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 教研组考核实体类
 * Created by wrb on 2017/5/13.
 */
public class JYZTitleEntity implements Serializable{
    private String value;//": 6,
            private String label;//": "主题"

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
