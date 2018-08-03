package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 值班实体
 * Created by wrb on 2017/4/24.
 */
public class BeOnDutyEntity implements Serializable{
            private String term;//": "2017~2018学年第一学期",
            private String jgname;//": "经济贸易系",
            private String trbd_status;//": "已生效",
            private String start_time;//": "2017-04-18",
            private String end_time;//": "2017-04-18",
            private String content;//": "sdasd",
            private String username;//": "小芳"

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getJgname() {
        return jgname;
    }

    public void setJgname(String jgname) {
        this.jgname = jgname;
    }

    public String getTrbd_status() {
        return trbd_status;
    }

    public void setTrbd_status(String trbd_status) {
        this.trbd_status = trbd_status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
