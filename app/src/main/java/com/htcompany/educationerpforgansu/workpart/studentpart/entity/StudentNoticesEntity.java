package com.htcompany.educationerpforgansu.workpart.studentpart.entity;

import com.htcompany.educationerpforgansu.workpart.entities.AllClassEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 学生公告实体类
 * Created by wrb on 2016/12/23.
 */
public class StudentNoticesEntity implements Serializable{
    private String wtype;//类型
    private String wtitle;//标题
    private String wcontent;//内容
    private String wtime;//发布时间
    private String wfbr;//发布人
    private List<AllClassEntity> classEntities;

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public String getWtitle() {
        return wtitle;
    }

    public void setWtitle(String wtitle) {
        this.wtitle = wtitle;
    }

    public String getWcontent() {
        return wcontent;
    }

    public void setWcontent(String wcontent) {
        this.wcontent = wcontent;
    }

    public String getWtime() {
        return wtime;
    }

    public void setWtime(String wtime) {
        this.wtime = wtime;
    }

    public String getWfbr() {
        return wfbr;
    }

    public void setWfbr(String wfbr) {
        this.wfbr = wfbr;
    }

    public List<AllClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<AllClassEntity> classEntities) {
        this.classEntities = classEntities;
    }
}
