package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 宿舍实体类
 * Created by wrb on 2017/4/24.
 */
public class DormRoomEntity implements Serializable{
      private String id;//": 9,
            private String name;//": "1121",
            private String num;//": "11",
            private String room_category_id;//": 2,
     private List<DormStudentEntity> students;

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(String room_category_id) {
        this.room_category_id = room_category_id;
    }

    public List<DormStudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<DormStudentEntity> students) {
        this.students = students;
    }
}

