package com.htcompany.educationerpforgansu.workpart.bookspart.entity;

import java.io.Serializable;

/**
 * 借阅记录
 * Created by wrb on 2017/2/20.
 */
public class BookReserveEntity implements Serializable{
           private String id;//": "jr6njgb8-58a6b7519eb54",
            private String isbn;//": "1605104",
            private String booktype;//": "4p1dvrce-586226fb24ab4",
            private String book;//": "lj0hcgkf-5864b67312e76",
            private String jieyue_type;//": 2,
            private String jieyueuid;//": "q8chlbtu-585a11d299092",
            private String jieyuetime;//": 1487260800,
            private String jingshouren;//": "9g65euuq-583ff68c9b0f5",
            private String adduid;//": "24u2q16i-5865c9bf8ade7",
            private String note;//": "22222222222222",
            private String ctime;//": 1487320912,
            private String rtime;//": 1487320912,
            private String guihuantime;//": null,
            private String ghjingshouren;//": null,
            private String ghuid;//": null,
            private String status;//": 1,
            private String jieyue_type_name;//": "学生",
            private String jieyue_time;//": "2017-02-17",
            private String jieyue_uname;//": "卢晓",
            private String jingshouren_name;//": "小新",
            private String guihuan_time;//": "",
            private String guihuan_name;//": "",
            private String status_name;//": "否",
            private BookEntity bookData;//图书详情
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getJieyue_type() {
        return jieyue_type;
    }

    public void setJieyue_type(String jieyue_type) {
        this.jieyue_type = jieyue_type;
    }

    public String getJieyueuid() {
        return jieyueuid;
    }

    public void setJieyueuid(String jieyueuid) {
        this.jieyueuid = jieyueuid;
    }

    public String getJieyuetime() {
        return jieyuetime;
    }

    public void setJieyuetime(String jieyuetime) {
        this.jieyuetime = jieyuetime;
    }

    public String getJingshouren() {
        return jingshouren;
    }

    public void setJingshouren(String jingshouren) {
        this.jingshouren = jingshouren;
    }

    public String getAdduid() {
        return adduid;
    }

    public void setAdduid(String adduid) {
        this.adduid = adduid;
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

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getGuihuantime() {
        return guihuantime;
    }

    public void setGuihuantime(String guihuantime) {
        this.guihuantime = guihuantime;
    }

    public String getGhjingshouren() {
        return ghjingshouren;
    }

    public void setGhjingshouren(String ghjingshouren) {
        this.ghjingshouren = ghjingshouren;
    }

    public String getGhuid() {
        return ghuid;
    }

    public void setGhuid(String ghuid) {
        this.ghuid = ghuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJieyue_type_name() {
        return jieyue_type_name;
    }

    public void setJieyue_type_name(String jieyue_type_name) {
        this.jieyue_type_name = jieyue_type_name;
    }

    public String getJieyue_time() {
        return jieyue_time;
    }

    public void setJieyue_time(String jieyue_time) {
        this.jieyue_time = jieyue_time;
    }

    public String getJieyue_uname() {
        return jieyue_uname;
    }

    public void setJieyue_uname(String jieyue_uname) {
        this.jieyue_uname = jieyue_uname;
    }

    public String getJingshouren_name() {
        return jingshouren_name;
    }

    public void setJingshouren_name(String jingshouren_name) {
        this.jingshouren_name = jingshouren_name;
    }

    public String getGuihuan_time() {
        return guihuan_time;
    }

    public void setGuihuan_time(String guihuan_time) {
        this.guihuan_time = guihuan_time;
    }

    public String getGuihuan_name() {
        return guihuan_name;
    }

    public void setGuihuan_name(String guihuan_name) {
        this.guihuan_name = guihuan_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public BookEntity getBookData() {
        return bookData;
    }

    public void setBookData(BookEntity bookData) {
        this.bookData = bookData;
    }
}
