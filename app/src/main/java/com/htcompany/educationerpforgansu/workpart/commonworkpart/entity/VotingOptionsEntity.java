package com.htcompany.educationerpforgansu.workpart.commonworkpart.entity;

import java.io.Serializable;

/**
 * 投票选项
 * Created by wrb on 2017/4/21.
 */
public class VotingOptionsEntity implements Serializable{
            private String name;//": "222",
            private String count;//": 2

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
