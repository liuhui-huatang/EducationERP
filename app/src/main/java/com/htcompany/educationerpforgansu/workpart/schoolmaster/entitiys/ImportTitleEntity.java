package com.htcompany.educationerpforgansu.workpart.schoolmaster.entitiys;

import java.io.Serializable;

/**
 * 主题实体类
 * Created by wrb on 2017/4/14.
 */
public class ImportTitleEntity implements Serializable{
    private String id;
    private String name;

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
