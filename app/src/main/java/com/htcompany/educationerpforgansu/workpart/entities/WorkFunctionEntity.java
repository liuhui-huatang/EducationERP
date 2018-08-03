package com.htcompany.educationerpforgansu.workpart.entities;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 办公功能实体类
 * Created by wrb on 2016/12/1.
 */
@Table(name = "WorkFunctionEntity")
public class WorkFunctionEntity implements Serializable{
    @Id
     private int id;
    private String funname;//功能名称
     private int funimg;//功能图标
     private String funflag;//跳转标示
     private String isadd;//添加标示
     private String isdelete;//删除标示
    private String ismain;//首页功能标示
    private String ispermission;//权限标示
    public String getFunname() {
        return funname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getIsmain() {
        return ismain;
    }

    public void setIsmain(String ismain) {
        this.ismain = ismain;
    }

    public String getIspermission() {
        return ispermission;
    }

    public void setIspermission(String ispermission) {
        this.ispermission = ispermission;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public int getFunimg() {
        return funimg;
    }

    public void setFunimg(int funimg) {
        this.funimg = funimg;
    }

    public String getFunflag() {
        return funflag;
    }

    public void setFunflag(String funflag) {
        this.funflag = funflag;
    }

    public String getIsadd() {
        return isadd;
    }

    public void setIsadd(String isadd) {
        this.isadd = isadd;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }
}
