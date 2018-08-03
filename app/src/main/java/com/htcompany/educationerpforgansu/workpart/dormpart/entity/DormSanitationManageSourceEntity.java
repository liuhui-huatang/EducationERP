package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 宿舍查询项分值
 * Created by wrb on 2017/4/26.
 */
public class DormSanitationManageSourceEntity implements Serializable{
     private String label;//": "清洁内务",
            private String value;//": "12"
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

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
