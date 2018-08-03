package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 宿舍评优查询
 * Created by wrb on 2017/4/26.
 */
public class AppraisingManageEntity implements Serializable{
           private String id;//": "63",
            private String date;//": "2017-01",
            private String area_id;//": "12",
            private String show_area_id;//": "全体评优",
            private String prize_id;//": "13",
            private String room_id;//": "18",
            private String room;//": "23",
            private String point;//": "9.00",
            private String build;//": "实验楼2",
            private String show_prize_id;//": "阿斯顿"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getShow_area_id() {
        return show_area_id;
    }

    public void setShow_area_id(String show_area_id) {
        this.show_area_id = show_area_id;
    }

    public String getPrize_id() {
        return prize_id;
    }

    public void setPrize_id(String prize_id) {
        this.prize_id = prize_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getShow_prize_id() {
        return show_prize_id;
    }

    public void setShow_prize_id(String show_prize_id) {
        this.show_prize_id = show_prize_id;
    }
}
