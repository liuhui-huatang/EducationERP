package com.htcompany.educationerpforgansu.workpart.entities;

import java.io.Serializable;

/**
 * 激光推送实体
 * Created by wrb on 2017/4/25.
 */
public class JpushWorkFlowEntity implements Serializable{
            private String flow_id;//":34,
            private String type;//":1,
            private String run_id;//":909,
            private String content;//":"fdsagdsg"}
            private String protype;//1待办事项，2业务跟踪详情

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRun_id() {
        return run_id;
    }

    public void setRun_id(String run_id) {
        this.run_id = run_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
