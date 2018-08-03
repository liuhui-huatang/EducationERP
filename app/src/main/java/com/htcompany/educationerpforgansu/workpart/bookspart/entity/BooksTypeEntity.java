package com.htcompany.educationerpforgansu.workpart.bookspart.entity;

import java.io.Serializable;

/**
 * 图书分类
 * Created by wrb on 2017/2/17.
 */
public class BooksTypeEntity implements Serializable{
            private String id;//": "crulrkqr-58a65873cdfe0",
            private String name;//": "3333qqqqq",
            private String code;//": "22222",
            private String pid;//": "tain4hop-58a64de90ed26",
            private String note;//": "333333",
            private String ctime;//": 1487296627

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
