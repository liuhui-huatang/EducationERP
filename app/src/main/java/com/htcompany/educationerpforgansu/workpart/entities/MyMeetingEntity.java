package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;
import java.util.List;

/**
 * 我的会议实体类
 * Created by wrb on 2016/12/13.
 */
public class MyMeetingEntity implements Serializable{
            private String id;//": 5,
            private String name;//": "123",
            private String number;//": "321",
            private String start;//": "2021-08-02",
            private String end;//": "2021-08-02",
            private String apply;//": null,
            private String statuss;//": "取消",
            private String status;//": 4,
            private String room;//": "",
            private String content;//": "",
    private String host;//主持人

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private List<String> mm_people_json;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMm_people_json() {
        return mm_people_json;
    }

    public void setMm_people_json(List<String> mm_people_json) {
        this.mm_people_json = mm_people_json;
    }
}
