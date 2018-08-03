package com.htcompany.educationerpforgansu.workpart.exampart.entitis;

import java.io.Serializable;

/**
 * 监考信息查询
 * Created by wrb on 2017/5/8.
 */
public class ExampartInvigilateEntity implements Serializable{
           private String username;//": "xueshengke",
            private String worker_number;//": "xueshengke",
            private String changci;//": "场次1",
            private String kaochang;//": "考场1",
            private String jiaoshi;//": "1123",
            private String cctime;//": "2017-04-26",
            private String ccstart;//": "08:40",
            private String ccend;//": "11:49"

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWorker_number() {
        return worker_number;
    }

    public void setWorker_number(String worker_number) {
        this.worker_number = worker_number;
    }

    public String getChangci() {
        return changci;
    }

    public void setChangci(String changci) {
        this.changci = changci;
    }

    public String getKaochang() {
        return kaochang;
    }

    public void setKaochang(String kaochang) {
        this.kaochang = kaochang;
    }

    public String getJiaoshi() {
        return jiaoshi;
    }

    public void setJiaoshi(String jiaoshi) {
        this.jiaoshi = jiaoshi;
    }

    public String getCctime() {
        return cctime;
    }

    public void setCctime(String cctime) {
        this.cctime = cctime;
    }

    public String getCcstart() {
        return ccstart;
    }

    public void setCcstart(String ccstart) {
        this.ccstart = ccstart;
    }

    public String getCcend() {
        return ccend;
    }

    public void setCcend(String ccend) {
        this.ccend = ccend;
    }
}
