package com.htcompany.educationerpforgansu.workpart.commonworkpart.entity;

import java.io.Serializable;
import java.util.List;

/**
 *投票实体类
 * Created by wrb on 2017/4/20.
 */
public class VotingManageEntity implements Serializable{
          private String id;//": 37,
            private String name;//": "zhuti",
            private String faqiren;//": "admin",
            private String header_img;//": "''",
            private String starttime;//": "2017-04-27",
            private String endtime;//": "2017-04-27",
            private String status;//": "未发布",
            private String vo_endtime_time;//": "剩余129个小时",
            private String count;
            private List<VotingOptionsEntity> options;//投票选项

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

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

    public String getFaqiren() {
        return faqiren;
    }

    public void setFaqiren(String faqiren) {
        this.faqiren = faqiren;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVo_endtime_time() {
        return vo_endtime_time;
    }

    public void setVo_endtime_time(String vo_endtime_time) {
        this.vo_endtime_time = vo_endtime_time;
    }

    public List<VotingOptionsEntity> getOptions() {
        return options;
    }

    public void setOptions(List<VotingOptionsEntity> options) {
        this.options = options;
    }
}
