package com.htcompany.educationerpforgansu;

/**
 * Created by shuimu on 2018/5/23.
 */

public class SignBean {

        private int id;
        private String userid;
        private String time;
        private int type;
        private String date;
        private int status;
        private String true_name;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
        public String getUserid() {
            return userid;
        }

        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public String getDate() {
            return date;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        public int getStatus() {
            return status;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }
        public String getTrue_name() {
            return true_name;
        }
}
