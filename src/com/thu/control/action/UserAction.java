package com.thu.control.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.opensymphony.xwork2.ActionSupport;
import com.thu.control.bean.UserBean;
import com.thu.control.dao.UserDAO;

public class UserAction extends ActionSupport {
	private int u_sn;
	private String u_account;
	private String u_password;
	private String u_name;
	private String u_phone;
	private String u_group;
	private ArrayList<UserBean> userList;
	private int userNumber;
	private int positionNumber;
	private String getInfoUserSn;
	private UserBean userInfo;
	private int editUserSn;
	private int deleteUserSn;
	private Map groupList;
	private Map jsonDataMap;

	public String getU_account() {
		return u_account;
	}

	public void setU_account(String u_account) {
		this.u_account = u_account;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_group() {
		return u_group;
	}

	public void setU_group(String u_group) {
		this.u_group = u_group;
	}

	public Map getJsonDataMap() {
		return jsonDataMap;
	}

	public void setJsonDataMap(Map jsonDataMap) {
		this.jsonDataMap = jsonDataMap;
	}

	public String goUserList() {
		userNumber = 0;
		userList = new ArrayList();
		UserDAO dao = new UserDAO();
		Map<Integer, UserBean> userMap = dao.findAllUser();
		SortedSet<Integer> keySet = new TreeSet<Integer>(userMap.keySet());
		for (int keys : keySet) {
			userNumber++;
			Set temp = userMap.get(keys).getGroup();
			String group = temp.toString().substring(1, temp.toString().length() - 1);
			UserBean bean = new UserBean();
			bean.setSn(userMap.get(keys).getSn());
			bean.setName(userMap.get(keys).getName());
			bean.setGroupString(group);
			userList.add(bean);
		}
		return "success";
	}

	public String getUniUserInfo() {
		UserDAO dao = new UserDAO();
		String[] info = getInfoUserSn.split("/");
		int UserSn = Integer.parseInt(info[0]);
		userInfo = dao.findUserBySn(UserSn);
		userInfo.setGroupString(info[1]);
		return "success";
	}

	public String goUserAdd() {
		UserDAO dao = new UserDAO();
		setGroupList(dao.findAllPosition());
		return "success";
	}

	public String insertUser() {
		jsonDataMap = new HashMap();
		UserDAO dao = new UserDAO();
		UserBean bean = new UserBean();
		if (dao.findUserByAccount(u_account).size() > 0) {
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "此帳號已有人使用");
		} else {
			bean.setAccount(u_account);
			bean.setPassword(u_password);
			bean.setGroupString(u_group);
			bean.setName(u_name);
			bean.setPhone(u_phone);
			if (dao.insertUser(bean) > 0) {
				jsonDataMap.put("state", true);
				jsonDataMap.put("msg", "新增成功");
			} else {
				jsonDataMap.put("state", false);
				jsonDataMap.put("msg", "新增失敗，請再次嘗試");
			}
		}
		return "success";
	}

	public String updateUser() {
		jsonDataMap = new HashMap();
		UserDAO dao = new UserDAO();
		UserBean bean = new UserBean();
		bean.setAccount(u_account);
		bean.setGroupString(u_group);
		bean.setPassword(u_password);
		bean.setName(u_name);
		bean.setPhone(u_phone);
		bean.setSn(u_sn);
		List<UserBean> userlist = dao.findUserByAccount(u_account);
		if (userlist.size() > 0) {
			if (userlist.get(0).getSn() != u_sn) {
				jsonDataMap.put("state", false);
				jsonDataMap.put("msg", "此帳號已有人使用");
			} else {
				if (dao.updateUser(bean) > 0) {
					jsonDataMap.put("state", true);
					jsonDataMap.put("msg", "修改成功");
				} else {
					jsonDataMap.put("state", false);
					jsonDataMap.put("msg", "修改失敗");
				}
			}
		} else {
			if (dao.updateUser(bean) > 0) {
				jsonDataMap.put("state", true);
				jsonDataMap.put("msg", "修改成功");
			} else {
				jsonDataMap.put("state", false);
				jsonDataMap.put("msg", "修改失敗");
			}
		}

		return "success";
	}

	public String goUserEdit() {
		UserDAO dao = new UserDAO();
		userInfo = dao.findUserBySn(editUserSn);
		String position = userInfo.getGroupString();
		Set groupSet = new HashSet();
		if (position.contains(",")) {
			String positions[] = position.split(",");
			for (int i = 0; i < positions.length; i++) {
				groupSet.add(positions[i]);
			}
		} else {
			groupSet.add(position);
		}
		userInfo.setGroupSet(groupSet);
		groupList = dao.findAllPosition();
		return "success";
	}

	public String deleteUser() {
		jsonDataMap = new HashMap();
		UserDAO dao = new UserDAO();
		if (dao.deleteUser(deleteUserSn) > 0) {
			jsonDataMap.put("state", true);
			jsonDataMap.put("msg", "刪除成功");
		} else {
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "刪除失敗，請再次嘗試");
		}
		return "success";
	}

	public String goUserDetail() {
		return "success";
	}

	public UserBean getUserInfo() {
		return userInfo;
	}

	public ArrayList<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserBean> userList) {
		this.userList = userList;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	public String getGetInfoUserSn() {
		return getInfoUserSn;
	}

	public void setGetInfoUserSn(String getInfoUserSn) {
		this.getInfoUserSn = getInfoUserSn;
	}

	public int getEditUserSn() {
		return editUserSn;
	}

	public void setEditUserSn(int editUserSn) {
		this.editUserSn = editUserSn;
	}

	public int getDeleteUserSn() {
		return deleteUserSn;
	}

	public void setDeleteUserSn(int deleteUserSn) {
		this.deleteUserSn = deleteUserSn;
	}

	public int getU_sn() {
		return u_sn;
	}

	public void setU_sn(int u_sn) {
		this.u_sn = u_sn;
	}

	public Map getGroupList() {
		return groupList;
	}

	public void setGroupList(Map groupList) {
		this.groupList = groupList;
	}
	public String goPositionList(){
		UserDAO dao = new UserDAO();
		groupList=dao.findAllPosition();
		positionNumber=groupList.size();
		return "success";
	}

	public int getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}
}
