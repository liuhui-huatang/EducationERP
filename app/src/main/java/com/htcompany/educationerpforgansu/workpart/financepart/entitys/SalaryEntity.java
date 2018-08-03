package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;
import java.util.List;

/**
 * 工资实体类
 * Created by wrb on 2017/4/10.
 */
public class SalaryEntity implements Serializable{
           private String id;//": 2,
            private String kahao;//": "kahao",
            private String money;//": 7,
            private String userName;//": "姓名",
            private String time;//": "2017-04-10",
            private String month;//": "04",
    private List<SalaryItemEntity> tiaomu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKahao() {
        return kahao;
    }

    public void setKahao(String kahao) {
        this.kahao = kahao;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<SalaryItemEntity> getTiaomu() {
        return tiaomu;
    }

    public void setTiaomu(List<SalaryItemEntity> tiaomu) {
        this.tiaomu = tiaomu;
    }
}
