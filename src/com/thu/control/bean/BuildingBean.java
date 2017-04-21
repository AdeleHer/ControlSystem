package com.thu.control.bean;

import java.util.List;

public class BuildingBean {
	private String buildingName;
	private String buildingEnName;
	private int buildingSn;
	private int roomNumber;
	private int floorNumber;
	public int getRoomNumber() {
		roomNumber=0;
		for(int i=0;i<floorBeanList.size();i++){
			roomNumber+=floorBeanList.get(i).getRoomBeanList().size()+1;
		}
		return roomNumber+1;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getFloorNumber() {
		return floorBeanList.size()+1;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	public BuildingBean(){
		
	}
	public BuildingBean(int buildingSn,String buildingName,String buildingEnName){
		this.buildingEnName=buildingEnName;
		this.buildingName=buildingName;
		this.buildingSn=buildingSn;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBuildingEnName() {
		return buildingEnName;
	}
	public void setBuildingEnName(String buildingEnName) {
		this.buildingEnName = buildingEnName;
	}
	public int getBuildingSn() {
		return buildingSn;
	}
	public void setBuildingSn(int buildingSn) {
		this.buildingSn = buildingSn;
	}
	public List<FloorBean> getFloorBeanList() {
		return floorBeanList;
	}
	public void setFloorBeanList(List<FloorBean> floorBeanList) {
		this.floorBeanList = floorBeanList;
	}
	private List<FloorBean> floorBeanList;
}
