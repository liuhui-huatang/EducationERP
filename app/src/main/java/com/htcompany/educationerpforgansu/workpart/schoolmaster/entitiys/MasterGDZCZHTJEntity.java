package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 固定资产统计实体
 * Created by wrb on 2017/5/9.
 */
public class MasterGDZCZHTJEntity implements Serializable{
            private String num;//": 8,
            private String type;//": 1,
            private String typeName;//": "交通运输设备"

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
