package com.htcompany.educationerpforgansu.commonpart.views;

import java.io.Serializable;

public class TimeTableModel implements Serializable{
	private String id;
	private int startnum;//开始节数
	private int endnum;//节数节数
	private int week;//星期几
	private String starttime="";//开始时间
	private String endtime="";//节数时间
	private String name="";//课程名称
	private String teacher="";//老师
	private String classroom="";//教室
	private String weeknum="";
	private String classname;//班级名称

	public String getIstodm() {
		return istodm;
	}

	public void setIstodm(String istodm) {
		this.istodm = istodm;
	}

	private String istodm;//1:调到点名列表，2不跳到点名列表
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "TimeTableModel [id=" + id + ", startnum=" + startnum
				+ ", endnum=" + endnum + ", week=" + week + ", starttime="
				+ starttime + ", endtime=" + endtime + ", name=" + name
				+ ", teacher=" + teacher + ", classroom=" + classroom
				+ ", weeknum=" + weeknum + "]";
	}

	public String getId() {
		return id;
	}

	public int getStartnum() {
		return startnum;
	}

	public int getEndnum() {
		return endnum;
	}

	public int getWeek() {
		return week;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getName() {
		return name;
	}

	public String getTeacher() {
		return teacher;
	}

	public String getClassroom() {
		return classroom;
	}

	public String getWeeknum() {
		return weeknum;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	public void setEndnum(int endnum) {
		this.endnum = endnum;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public void setWeeknum(String weeknum) {
		this.weeknum = weeknum;
	}
	public TimeTableModel() {
		// TODO Auto-generated constructor stub
	}

	public TimeTableModel(String id, int startnum, int endnum, int week,
						  String starttime, String endtime, String name, String teacher,
						  String classroom, String weeknum) {
		super();
		this.id = id;
		this.startnum = startnum;
		this.endnum = endnum;
		this.week = week;
		this.starttime = starttime;
		this.endtime = endtime;
		this.name = name;
		this.teacher = teacher;
		this.classroom = classroom;
		this.weeknum = weeknum;
	}

}
