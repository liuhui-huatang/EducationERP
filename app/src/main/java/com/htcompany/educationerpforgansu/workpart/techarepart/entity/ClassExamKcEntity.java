package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * Created by wrb on 2017/4/24.
 */
public class ClassExamKcEntity implements Serializable{
    private String name;//": "PHP基础",
    private String fenshu;//": 20

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFenshu() {
        return fenshu;
    }

    public void setFenshu(String fenshu) {
        this.fenshu = fenshu;
    }
}
