package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 请假类型实体
 * Created by weiruibin on 2017/6/1.
 */

public class MyLeaveTypeEntity implements Serializable{
    private String id;//": "hnt8vr5g-5837d03f562a8",
            private String name;//": "病假"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
