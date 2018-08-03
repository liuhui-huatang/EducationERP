package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;

/**
 * 退费记录实体类
 * Created by wrb on 2017/4/10.
 */
public class ReturnMoneyEntity implements Serializable{
            private String money;//": "108.00",
            private String time;//": "2017-03-23",
            private String jiaoxueban;//": "教学20班",
            private String banzhuren;//": "admin",
            private String xuesheng;//": "新生21",
            private String xuehao;//": "1221",
            private String fengcun;//": "未封存",
            private String fengcunUser;//": "",
            private String fengcunTime;//": ""

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJiaoxueban() {
        return jiaoxueban;
    }

    public void setJiaoxueban(String jiaoxueban) {
        this.jiaoxueban = jiaoxueban;
    }

    public String getBanzhuren() {
        return banzhuren;
    }

    public void setBanzhuren(String banzhuren) {
        this.banzhuren = banzhuren;
    }

    public String getXuesheng() {
        return xuesheng;
    }

    public void setXuesheng(String xuesheng) {
        this.xuesheng = xuesheng;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getFengcun() {
        return fengcun;
    }

    public void setFengcun(String fengcun) {
        this.fengcun = fengcun;
    }

    public String getFengcunUser() {
        return fengcunUser;
    }

    public void setFengcunUser(String fengcunUser) {
        this.fengcunUser = fengcunUser;
    }

    public String getFengcunTime() {
        return fengcunTime;
    }

    public void setFengcunTime(String fengcunTime) {
        this.fengcunTime = fengcunTime;
    }
}
