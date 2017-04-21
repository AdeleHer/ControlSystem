package com.thu.control.bean;

import java.util.List;
import java.util.Set;

public class RoomBean {
	private int roomSn;
	private String roomName;
	private String roomRemark;
	private Set scheduleCount;
	private List<CourseBean> courseList;
	public RoomBean(){
		
	}
	public RoomBean(int roomSn,String roomName,String roomRemark){
		this.roomSn=roomSn;
		this.roomName=roomName;
		this.roomRemark=roomRemark;
	}
	public int getRoomSn() {
		return roomSn;
	}
	public void setRoomSn(int roomSn) {
		this.roomSn = roomSn;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomRemark() {
		return roomRemark;
	}
	public void setRoomRemark(String roomRemark) {
		this.roomRemark = roomRemark;
	}
	public Set getScheduleCount() {
		return scheduleCount;
	}
	public void setScheduleCount(Set scheduleCount) {
		this.scheduleCount = scheduleCount;
	}
	public List<CourseBean> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<CourseBean> courseList) {
		this.courseList = courseList;
	}
}
