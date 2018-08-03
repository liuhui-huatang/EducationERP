package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 点名册实体类
 * Created by wrb on 2017/1/22.
 */
public class ClassDMCEntity implements Serializable{
            private String uid;//": "1pi1922a-587f375caffa8",
            private String truename;//": "新生19",
            private String photo;//": null
             private List<ClassDMStatusEntity> shuju;

    public List<ClassDMStatusEntity> getShuju() {
        return shuju;
    }

    public void setShuju(List<ClassDMStatusEntity> shuju) {
        this.shuju = shuju;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
