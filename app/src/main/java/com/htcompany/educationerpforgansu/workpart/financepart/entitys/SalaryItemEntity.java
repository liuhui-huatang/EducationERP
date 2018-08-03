package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;

/**
 * 收费条目实体类
 * Created by wrb on 2017/4/10.
 */
public class SalaryItemEntity implements Serializable{
    private String name;//": "扣款",
    private String count;//": "-3"

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
