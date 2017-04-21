package com.thu.control.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.thu.control.bean.PositionBean;
import com.thu.control.bean.UserBean;
import com.thu.control.dao.PositionDAO;
import com.thu.control.dao.UserDAO;

public class PositionAction extends ActionSupport{
	private int p_sn;
	private String p_name;
	private PositionBean position;
	private Map jsonDataMap;
	private ArrayList effectList;
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String goPositionUpdate(){
		PositionDAO dao=new PositionDAO();
		position=dao.findPositionBySn(p_sn);
		return "success";
	}
	public String insertPosition(){
		jsonDataMap=new HashMap();
		PositionDAO dao=new PositionDAO();
		if(dao.insertPosition(p_name)>0){
			jsonDataMap.put("state", true);
			jsonDataMap.put("msg", "新增成功");
		}else{
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "新增失敗");
		}
		return "success";
	}
	public String updatePosition(){
		jsonDataMap=new HashMap();
		PositionDAO dao=new PositionDAO();
		PositionBean bean=new PositionBean(p_sn,p_name);
		if(dao.updatePosition(bean)>0){
			jsonDataMap.put("state", true);
			jsonDataMap.put("msg", "修改成功");
		}else{
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "修改失敗");
		}
		return "success";
	}
	public String deletePosition(){
		jsonDataMap=new HashMap();
		effectList=new ArrayList();
		PositionDAO dao=new PositionDAO();
		UserDAO udao=new UserDAO();
		ArrayList elist=dao.updateDeletePositionEffect(p_sn);
		for(int i=0;i<elist.size();i++){
			UserBean bean=udao.findUserBySn(Integer.parseInt(elist.get(i).toString()));
			effectList.add("["+bean.getSn()+","+bean.getName()+"]");
		}
		if(dao.deletePosition(p_sn)>0){
			jsonDataMap.put("state", true);
			jsonDataMap.put("msg", "刪除成功");
		}else{
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "刪除失敗");
		}
		return "success";
	}
	public int getP_sn() {
		return p_sn;
	}
	public void setP_sn(int p_sn) {
		this.p_sn = p_sn;
	}
	public Map getJsonDataMap() {
		return jsonDataMap;
	}
	public void setJsonDataMap(Map jsonDataMap) {
		this.jsonDataMap = jsonDataMap;
	}
	public PositionBean getPosition() {
		return position;
	}
	public void setPosition(PositionBean position) {
		this.position = position;
	}
	public ArrayList getEffectList() {
		return effectList;
	}
	public void setEffectList(ArrayList effectList) {
		this.effectList = effectList;
	}
}
