package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;

/**
 * 收费记录条目实体
 * Created by wrb on 2017/4/10.
 */
public class RealityChargeItemEntity implements Serializable{
    private String name;//": "考试费",
            private String count;//": 100

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
