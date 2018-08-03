package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 教研组考核汇总实体类
 * Created by wrb on 2017/4/13.
 */
public class JYZKHHZEntity implements Serializable{
    private String name;//": "电子教研组",
            private String totalScores;//": 0,
            private String sort;//": 1

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(String totalScores) {
        this.totalScores = totalScores;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
