package com.htcompany.educationerpforgansu.workpart.financepart.entitys;

import java.io.Serializable;

/**
 * 费用统计
 * Created by wrb on 2017/4/10.
 */
public class StatisticsEntity implements Serializable{
            private String major;//": "科技",
            private String grade;//": "2017春级",
            private String length;//": "两年制",
            private String levels;//": "三年制中职",
            private String count;//": 1,
            private String yingshou;//": 600,
            private String tiaozheng;//": 0,
            private String yishou;//": 0,
            private String qianfei;//": 600,
            private String renshu;//": 1

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getYingshou() {
        return yingshou;
    }

    public void setYingshou(String yingshou) {
        this.yingshou = yingshou;
    }

    public String getTiaozheng() {
        return tiaozheng;
    }

    public void setTiaozheng(String tiaozheng) {
        this.tiaozheng = tiaozheng;
    }

    public String getYishou() {
        return yishou;
    }

    public void setYishou(String yishou) {
        this.yishou = yishou;
    }

    public String getQianfei() {
        return qianfei;
    }

    public void setQianfei(String qianfei) {
        this.qianfei = qianfei;
    }

    public String getRenshu() {
        return renshu;
    }

    public void setRenshu(String renshu) {
        this.renshu = renshu;
    }
}
