package com.htcompany.educationerpforgansu.homepart.entity;

import java.io.Serializable;

/**
 * 我的惩罚实体类
 * Created by wrb on 2017/4/20.
 */
public class MyPunishmentEntity implements Serializable{
           private String name;//": "惩处名称",
            private String username;//": "陈鸿",
            private String content;//": "惩处内容\n",
            private String time;//": "2017-01-12",
            private String number;//": "惩处文号",
            private String cause;//": "惩处原因\n",
            private String un_number;//": "asdfafa",
            private String un_punish_time;//": "2017-01-18",
            private String un_cause;//": "asdfaf"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getUn_number() {
        return un_number;
    }

    public void setUn_number(String un_number) {
        this.un_number = un_number;
    }

    public String getUn_punish_time() {
        return un_punish_time;
    }

    public void setUn_punish_time(String un_punish_time) {
        this.un_punish_time = un_punish_time;
    }

    public String getUn_cause() {
        return un_cause;
    }

    public void setUn_cause(String un_cause) {
        this.un_cause = un_cause;
    }
}
