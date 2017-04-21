package com.thu.control.bean;

import java.util.List;

public class FloorBean {
	private int floorSn;
	private String floorName;
	private List<RoomBean> roomBeanList;
	private int roomNumber;
	public FloorBean(){
		
	}
	public FloorBean(int floorSn,String floorName){
		this.floorName=floorName;
		this.floorSn=floorSn;
	}
	public int getFloorSn() {
		return floorSn;
	}
	public void setFloorSn(int floorSn) {
		this.floorSn = floorSn;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public List<RoomBean> getRoomBeanList() {
		return roomBeanList;
	}
	public void setRoomBeanList(List<RoomBean> roomBeanList) {
		this.roomBeanList = roomBeanList;
	}
	public int getRoomNumber() {
		return roomBeanList.size()+1;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
}
