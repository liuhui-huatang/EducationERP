package com.htcompany.educationerpforgansu.messagepart.entity;

import java.io.Serializable;

/**
 * 消息实体
 * Created by wrb on 2017/4/25.
 */
public class MainMessageEntity implements Serializable{
               private String id;//": 303,
               private String theme;//": "有新的工作需要审核",
               private String addTime;//": "2017-04-28 13:55:16",
               private String content;//": "有新的工作需要审核",
               private String is_read;//": "未读",
               private String true_name;//": "张三"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }
}
