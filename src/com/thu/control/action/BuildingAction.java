package com.thu.control.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.thu.control.bean.BuildingBean;
import com.thu.control.bean.CourseBean;
import com.thu.control.bean.RoomBean;
import com.thu.control.dao.BaseDAO;
import com.thu.control.dao.BuildingDAO;
import com.thu.control.dao.CourseDAO;
import com.thu.control.program.EditBuilding;

public class BuildingAction extends ActionSupport{
	private int editSn;
	private List<BuildingBean> buildingList;
	private File editBuildingFile;
	private String editBuildingFileName;
	private Map jsonDataMap;
	private BuildingBean editBuildBean;
	private List<RoomBean> roomList;
	public String goBuildingList(){
		BuildingDAO bdao=new BuildingDAO();
		buildingList=bdao.getAllTbBuilding();
		return "success";
	}
	public String goBuildingUpload(){
		BuildingDAO bdao=new BuildingDAO();
		editBuildBean=bdao.selectBuildingBySn(editSn);
		return "success";
	}
	public String goFloorUpload(){
		BuildingDAO bdao=new BuildingDAO();
		editBuildBean=bdao.selectFloorBySn(editSn);
		return "success";
	}
	public String goRoomUpload(){
		BuildingDAO bdao=new BuildingDAO();
		editBuildBean=bdao.selectRoomBySn(editSn);
		return "success";
	}
	public String goRoomList(){
		BuildingDAO bdao=new BuildingDAO();
		CourseDAO cdao=new CourseDAO();
		roomList=bdao.getAllRoom();
		roomList=cdao.getRelCourseByRoomSn(roomList);
		for(int i=0;i<roomList.size();i++){
			RoomBean temp=roomList.get(i);
			Set courseSet=temp.getScheduleCount();
			List<CourseBean> cList=new ArrayList();
			if(courseSet!=null){
				List sortedList = new ArrayList(courseSet);
				Collections.sort(sortedList);
				for(int j=0;j<sortedList.size();j++){
					CourseBean cBean=cdao.selectCourseBySn(Integer.parseInt(sortedList.get(j).toString()));
					cList.add(cBean);
				}
			}
			temp.setCourseList(cList);
			roomList.set(i, temp);
		}
		return "success";
	}
/*	public String uploadEditBuildingFile(){
		jsonDataMap=new HashMap();
		String subName=editBuildingFileName.substring(editBuildingFileName.lastIndexOf(".")+1);
		if(subName.equals("xlsx")){
			String storePath="D:/THUControl/uploadFile/";
			File folder=new File(storePath);
			if(!folder.exists()){
				folder.mkdirs();
			}
			Date now=new Date();
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmm");
			storePath+="編輯建築物-"+sdFormat.format(now)+".xlsx";
			File f=new File(storePath);
			try {
				FileUtils.copyFile(editBuildingFile, f);
				EditBuilding program=new EditBuilding(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			jsonDataMap.put("state", false);
			jsonDataMap.put("msg", "不符合檔案格式xlsx");
		}
		
		return "success";
	}*/
	public List<BuildingBean> getBuildingList() {
		return buildingList;
	}
	public void setBuildingList(List<BuildingBean> buildingList) {
		this.buildingList = buildingList;
	}
	public File getEditBuildingFile() {
		return editBuildingFile;
	}
	public void setEditBuildingFile(File editBuildingFile) {
		this.editBuildingFile = editBuildingFile;
	}
	public String getEditBuildingFileName() {
		return editBuildingFileName;
	}
	public void setEditBuildingFileName(String editBuildingFileName) {
		this.editBuildingFileName = editBuildingFileName;
	}
	public Map getJsonDataMap() {
		return jsonDataMap;
	}
	public void setJsonDataMap(Map jsonDataMap) {
		this.jsonDataMap = jsonDataMap;
	}
	public int getEditSn() {
		return editSn;
	}
	public void setEditSn(int editSn) {
		this.editSn = editSn;
	}
	public BuildingBean getEditBuildBean() {
		return editBuildBean;
	}
	public void setEditBuildBean(BuildingBean editBuildBean) {
		this.editBuildBean = editBuildBean;
	}
	public List<RoomBean> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomBean> roomList) {
		this.roomList = roomList;
	}
}
