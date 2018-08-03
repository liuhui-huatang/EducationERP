package com.htcompany.educationerpforgansu.workpart.exampart.entitis;

import java.io.Serializable;

/**
 * 考试项目实体
 * Created by wrb on 2017/5/8.
 */
public class ExamProjectEntity implements Serializable{
            private String name;//": "考试项目one",
            private String unkey;//": "u499gl4a-58fec2de07212"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnkey() {
        return unkey;
    }

    public void setUnkey(String unkey) {
        this.unkey = unkey;
    }
}
