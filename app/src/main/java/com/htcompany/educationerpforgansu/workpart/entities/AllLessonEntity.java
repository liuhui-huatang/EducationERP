package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 课程选择界面
 * Created by wrb on 2017/4/20.
 */
public class AllLessonEntity implements Serializable{
            private String label;//": "课程",
            private String html;//": "<div class=\"dtcource\"><span>星期</span><span>节次</span><span>课程</span></div>",
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
