package com.thu.control.bean;

public class PositionBean {
	private int sn;
	private String name;
	public PositionBean(int sn,String name){
		this.sn=sn;
		this.name=name;
	}
	public PositionBean(){}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
