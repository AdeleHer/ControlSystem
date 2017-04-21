package com.thu.control.bean;

import java.util.Set;

public class UserBean {
	private String account;
	private int sn;
	private int groupSn;
	private Set<Integer> groupSet;
	private String password;
	private String name;
	private String phone;
	private Set<String> group;
	private String groupString;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Set getGroup() {
		return group;
	}
	public void setGroup(Set group) {
		this.group = group;
	}
	public String getGroupString() {
		return groupString;
	}
	public void setGroupString(String groupString) {
		this.groupString = groupString;
	}
	public int getGroupSn() {
		return groupSn;
	}
	public void setGroupSn(int groupSn) {
		this.groupSn = groupSn;
	}
	public Set<Integer> getGroupSet() {
		return groupSet;
	}
	public void setGroupSet(Set<Integer> groupSet) {
		this.groupSet = groupSet;
	}
	
}
