package com.htcompany.educationerpforgansu.commonpart.views.bannerview;

import java.io.Serializable;

/**
 * 广告实体类
 * Created by wrb on 2016/12/8.
 */
public class BannarEntity implements Serializable{
    private String btitle;//标题
    private int blogo;//图片
    private String burl;//路径

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public int getBlogo() {
        return blogo;
    }

    public void setBlogo(int blogo) {
        this.blogo = blogo;
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl;
    }
}

