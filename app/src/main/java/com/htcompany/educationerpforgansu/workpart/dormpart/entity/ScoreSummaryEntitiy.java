package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 卫生得分汇总实体类
 * Created by wrb on 2017/4/26.
 */
public class ScoreSummaryEntitiy implements Serializable{
           private String name;//": "行政20班",
            private String score;//": "22.80",
            private String username;//": "admin"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
