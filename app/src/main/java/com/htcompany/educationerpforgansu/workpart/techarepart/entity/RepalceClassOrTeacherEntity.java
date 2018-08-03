package com.htcompany.educationerpforgansu.workpart.techarepart.entity;

import java.io.Serializable;

/**
 * 代课调课实体类
 * Created by wrb on 2017/4/22.
 */
public class RepalceClassOrTeacherEntity implements Serializable{
            private String label;//": "姓名",
            private String html;//": "<div class=\"dtcource\"><span>工号</span><span>姓名</span></div>",
            private String value;//": 0,
            private String disabled;//": true

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
