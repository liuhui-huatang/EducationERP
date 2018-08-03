package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 班级公告
 * Created by wrb on 2016/12/21.
 */
public class ClassNotcieEntity implements Serializable{
           private String id;//": 17,
            private String title;//": "1111",
            private String content;//": "111111",
            private String time;//": "2017-01-16"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
