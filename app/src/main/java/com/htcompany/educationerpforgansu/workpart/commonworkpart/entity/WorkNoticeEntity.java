package com.htcompany.educationerpforgansu.workpart.commonworkpart.entity;

import java.io.Serializable;

/**
 * 办公公告实体类
 * Created by wrb on 2016/12/14.
 */
public class WorkNoticeEntity implements Serializable{
            private String id;//": 9,
            private String b_title;//": "123131",
            private String b_sender;//": "attgq7ee-583b9a599e11c",
            private String b_send_date;//": "2017-01-18",
            private String b_send_type;//": "全校公告",
            private String b_content;//": "<p>321312313131</p>",
            private String username;//": "admin"
            private String type;//": 2,

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_sender() {
        return b_sender;
    }

    public void setB_sender(String b_sender) {
        this.b_sender = b_sender;
    }

    public String getB_send_date() {
        return b_send_date;
    }

    public void setB_send_date(String b_send_date) {
        this.b_send_date = b_send_date;
    }

    public String getB_send_type() {
        return b_send_type;
    }

    public void setB_send_type(String b_send_type) {
        this.b_send_type = b_send_type;
    }

    public String getB_content() {
        return b_content;
    }

    public void setB_content(String b_content) {
        this.b_content = b_content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
