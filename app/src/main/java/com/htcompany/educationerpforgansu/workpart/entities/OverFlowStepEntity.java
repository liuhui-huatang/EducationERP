package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;
import java.util.List;

/**
 * 已办工作流步骤名称
 * Created by wrb on 2017/4/23.
 */
public class OverFlowStepEntity implements Serializable{
    private String name;//": "shenhe",
            private String status;//": "已办理(通过)",
    private List<OverFlowItemEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OverFlowItemEntity> getUsers() {
        return users;
    }

    public void setUsers(List<OverFlowItemEntity> users) {
        this.users = users;
    }
}
