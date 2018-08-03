package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 学生状态统计
 * Created by wrb on 2017/5/11.
 */
public class MasterXSZTEntity implements Serializable{
             private String value;//": 1,
            private String name;//": "休学"

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
