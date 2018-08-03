package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 地址实体类
 * Created by wrb on 2016/12/13.
 */
public class AllAddressEntity implements Serializable{
            private String key;//": 15,
            private String name;//": "1"

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
