package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;

/**
 * 宿舍学生实体类
 * Created by wrb on 2017/4/24.
 */
public class DormStudentEntity implements Serializable{
          private String id;//": 4,
            private String room_id;//": 9,
            private String stuid;//": "u57u4j9a-58800e681a9b1",
            private String truename;//": "新生21"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }
}
