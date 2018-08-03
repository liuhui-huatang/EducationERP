package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 年级实体类
 * Created by wrb on 2017/5/11.
 */
public class GradEntity implements Serializable{
    private String label;//": "2017秋级",
            private String value;//": "u1qm52sv-58293c6eb069c"

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
