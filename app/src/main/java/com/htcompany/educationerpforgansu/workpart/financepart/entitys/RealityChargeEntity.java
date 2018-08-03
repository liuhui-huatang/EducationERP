package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;
import java.util.List;

/**
 * 收费记录实体
 * Created by wrb on 2017/4/10.
 */
public class RealityChargeEntity implements Serializable{
            private String id;//": 20,
            private String time;//": "2017-04-07",
            private String biaozhun;//": "标准1",
            private String money;//": "29.00",
            private String project_name;//": "yyyy",
            private String shouju;//": "",
            private String shoukuan;//": "姓名",
            private String fengcun;//": "",
            private String jiaoxueban;//": "教学20班",
            private String xuesheng;//": "新生21",
            private String xuehao;//": "1221",
            private String fengcunTime;//": "",
            private String fengcunStatus;//": "未封存"

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getFengcunTime() {
        return fengcunTime;
    }

    public void setFengcunTime(String fengcunTime) {
        this.fengcunTime = fengcunTime;
    }

    public String getFengcunStatus() {
        return fengcunStatus;
    }

    public void setFengcunStatus(String fengcunStatus) {
        this.fengcunStatus = fengcunStatus;
    }

    public String getBanzhuren() {
        return banzhuren;
    }

    public void setBanzhuren(String banzhuren) {
        this.banzhuren = banzhuren;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBiaozhun() {
        return biaozhun;
    }

    public void setBiaozhun(String biaozhun) {
        this.biaozhun = biaozhun;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getShouju() {
        return shouju;
    }

    public void setShouju(String shouju) {
        this.shouju = shouju;
    }

    public String getShoukuan() {
        return shoukuan;
    }

    public void setShoukuan(String shoukuan) {
        this.shoukuan = shoukuan;
    }

    public String getFengcun() {
        return fengcun;
    }

    public void setFengcun(String fengcun) {
        this.fengcun = fengcun;
    }

    public String getJiaoxueban() {
        return jiaoxueban;
    }

    public void setJiaoxueban(String jiaoxueban) {
        this.jiaoxueban = jiaoxueban;
    }

    public String getXuesheng() {
        return xuesheng;
    }

    public void setXuesheng(String xuesheng) {
        this.xuesheng = xuesheng;
    }

    private String banzhuren;//": "admin",
     private List<RealityChargeItemEntity> tiaomu;

    public List<RealityChargeItemEntity> getTiaomu() {
        return tiaomu;
    }

    public void setTiaomu(List<RealityChargeItemEntity> tiaomu) {
        this.tiaomu = tiaomu;
    }
}
