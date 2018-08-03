package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 日程实体类
 * Created by wrb on 2016/11/7.
 */
public class ScheduleEntity implements Serializable{
            private String id;//": "6",
             private String s_time;//": "2017-05-25 13:00:00",
             private String e_time;//": "2017-05-25 23:59:59",
             private String message;//": "请客户吃饭",
             private String unkey;//": "1115pcgc_592656a9b35b3",
             private String uid;//": "hfljsl0m-584e3b91a48e8"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUnkey() {
        return unkey;
    }

    public void setUnkey(String unkey) {
        this.unkey = unkey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
