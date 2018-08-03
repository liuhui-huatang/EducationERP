package com.htcompany.educationerpforgansu.workpart.dormpart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 宿舍楼实体列
 * Created by wrb on 2017/4/24.
 */
public class DormBulingEntitiy implements Serializable{
    private String id;//": 2,
            private String building_name;//": "实验楼2",
    private List<DormRoomEntity> room;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public List<DormRoomEntity> getRoom() {
        return room;
    }

    public void setRoom(List<DormRoomEntity> room) {
        this.room = room;
    }
}
