package com.thu.control.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thu.control.bean.BuildingBean;
import com.thu.control.bean.FloorBean;
import com.thu.control.bean.RoomBean;

public class BuildingDAO {
	public List<BuildingBean> getAllTbBuilding() {
		List<BuildingBean> rbList = new ArrayList();
		BaseDAO base = new BaseDAO();
		base.sql = "select * from tb_building";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			while (base.rs.next()) {
				BuildingBean temp = new BuildingBean(base.rs.getInt("sn"), base.rs.getString("name"),
						base.rs.getString("en"));
				rbList.add(temp);
			}
			for (int i = 0; i < rbList.size(); i++) {
				BuildingBean temp = rbList.get(i);
				List<FloorBean> rfList = new ArrayList();
				base.sql = "select * from tb_floor where building_sn=" + temp.getBuildingSn();
				base.stmt = base.conn.createStatement();
				base.rs = base.stmt.executeQuery(base.sql);
				while (base.rs.next()) {
					FloorBean ftemp = new FloorBean(base.rs.getInt("sn"), base.rs.getString("name"));
					rfList.add(ftemp);
				}
				for (int j = 0; j < rfList.size(); j++) {
					FloorBean ftemp = rfList.get(j);
					List<RoomBean> rrList = new ArrayList();
					base.sql = "select * from tb_room where floor_sn=" + ftemp.getFloorSn();
					base.stmt = base.conn.createStatement();
					base.rs = base.stmt.executeQuery(base.sql);
					while (base.rs.next()) {
						RoomBean rtemp = new RoomBean(base.rs.getInt("sn"), base.rs.getString("name"),
								base.rs.getString("remark"));
						rrList.add(rtemp);
					}
					ftemp.setRoomBeanList(rrList);
					rfList.set(j, ftemp);
				}
				temp.setFloorBeanList(rfList);
				rbList.set(i, temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				base.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rbList;

	}
	
	public BuildingBean selectBuildingBySn(int sn){
		BuildingBean bean=new BuildingBean();
		BaseDAO base=new BaseDAO();
		base.sql="select * from tb_building where sn="+sn;
		try{
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			if(base.rs.next()){
				bean.setBuildingSn(base.rs.getInt("sn"));
				bean.setBuildingName(base.rs.getString("name"));
				bean.setBuildingEnName(base.rs.getString("en"));
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	public BuildingBean selectFloorBySn(int sn){
		BuildingBean bean=new BuildingBean();
		FloorBean fBean=new FloorBean();
		BaseDAO base=new BaseDAO();
		base.sql="select * from tb_floor where sn="+sn;
		try{
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			if(base.rs.next()){
				fBean.setFloorName(base.rs.getString("name"));
				fBean.setFloorSn(base.rs.getInt("sn"));
				int building_sn=base.rs.getInt("building_sn");
				bean = selectBuildingBySn(building_sn);
				List<FloorBean> fList=new ArrayList();
				fList.add(fBean);
				bean.setFloorBeanList(fList);
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	public BuildingBean selectRoomBySn(int sn){
		BuildingBean bean=new BuildingBean();
		BaseDAO base=new BaseDAO();
		RoomBean rBean=new RoomBean();
		FloorBean fBean=new FloorBean();
		base.sql="select * from tb_room where sn="+sn;
		try{
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			if(base.rs.next()){
				rBean.setRoomSn(base.rs.getInt("sn"));
				rBean.setRoomName(base.rs.getString("name"));
				rBean.setRoomRemark(base.rs.getString("remark"));
				bean = selectFloorBySn(base.rs.getInt("floor_sn"));
				fBean = bean.getFloorBeanList().get(0);
				List<RoomBean> rList=new ArrayList();
				rList.add(rBean);
				fBean.setRoomBeanList(rList);
				List<FloorBean> fList=new ArrayList();
				fList.add(fBean);
				bean.setFloorBeanList(fList);
			}else{
				return null;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	public List<RoomBean> getAllRoom(){
		List<RoomBean> rList=new ArrayList();
		BaseDAO base=new BaseDAO();
		base.sql="select * from tb_room";
		try{
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			while(base.rs.next()){
				RoomBean bean=new RoomBean(base.rs.getInt("sn"),base.rs.getString("name"),base.rs.getString("remark"));
				rList.add(bean);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}
/*	public List<BuildingBean> getOnlyBuilding() {
		List<BuildingBean> rbList = new ArrayList();
		BaseDAO base = new BaseDAO();
		base.sql = "select * from tb_building";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			while (base.rs.next()) {
				BuildingBean temp = new BuildingBean();
				temp.setBuildingSn(base.rs.getInt("sn"));
				temp.setBuildingName(base.rs.getString("name") + "(" + base.rs.getString("en") + ")");
				rbList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rbList;
	}

	public Integer putBuilding(ArrayList<String> array) {
		BaseDAO base = new BaseDAO();
		BuildingBean bean = new BuildingBean();
		base.sql = "select * from tb_building where name=? or en=?";
		try {
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, array.get(0));
			base.pstmt.setString(2, array.get(1));
			base.rs = base.pstmt.executeQuery();
			int count = 0;
			while (base.rs.next()) {
				count++;
				bean.setBuildingSn(base.rs.getInt("sn"));
				bean.setBuildingName(base.rs.getString("name"));
				bean.setBuildingEnName(base.rs.getString("en"));
			}
			if (count > 1) {
				return 2;
			} else if (count == 1) {
				base.sql = "update tb_building set name=?,en=? where sn=?";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setString(1, array.get(0));
				base.pstmt.setString(2, array.get(1));
				base.pstmt.setInt(3, bean.getBuildingSn());
				base.result = base.pstmt.executeUpdate();
				if (base.result > 0) {
					base.conn.commit();
				} else {
					base.conn.rollback();
					return 3;
				}
				return 1;
			} else {
				base.sql = "insert into tb_building (name,en) values (?,?)";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setString(1, array.get(0));
				base.pstmt.setString(2, array.get(1));
				base.result = base.pstmt.executeUpdate();
				if (base.result > 0) {
					base.conn.commit();
				} else {
					base.conn.rollback();
					return 3;
				}
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 3;
	}

	public Integer putFloor(ArrayList<String> array) {
		BaseDAO base = new BaseDAO();
		try {
			int result = putBuilding(array);
			if (result == 0) {
				int building_sn = 0;
				base.sql = "select sn from tb_building where sn=@@identity";
				base.stmt = base.conn.createStatement();
				base.rs = base.stmt.executeQuery(base.sql);
				if (base.rs.next()) {
					building_sn = base.rs.getInt("sn");
					base.sql = "insert into tb_floor (building_sn,name) values (?,?)";
					base.pstmt = base.conn.prepareStatement(base.sql);
					base.pstmt.setInt(1, building_sn);
					base.pstmt.setString(2, array.get(2));
					base.result = base.pstmt.executeUpdate();
					if (base.result > 0) {
						base.conn.commit();
						return 0;
					} else {
						base.conn.rollback();
						return 3;
					}
				} else {
					return 3;
				}
			} else if (result == 1) {
				int building_sn = 0;
				base.sql = "select sn from tb_building where name=? and en=?";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setString(1, array.get(0));
				base.pstmt.setString(2, array.get(1));
				base.rs = base.pstmt.executeQuery();
				if (base.rs.next()) {
					building_sn = base.rs.getInt("sn");
					base.sql = "select * from tb_floor where name=?";
					base.pstmt = base.conn.prepareStatement(base.sql);
					base.pstmt.setString(1, array.get(2));
					base.rs = base.pstmt.executeQuery();
					if (!base.rs.next()) {
						base.sql = "insert into tb_floor (building_sn,name) values (?,?)";
						base.pstmt = base.conn.prepareStatement(base.sql);
						base.pstmt.setInt(1, building_sn);
						base.pstmt.setString(2, array.get(2));
						base.result = base.pstmt.executeUpdate();
						if (base.result > 0) {
							base.conn.commit();
							return 0;
						} else {
							base.conn.rollback();
							return 3;
						}
					} else {
						return 1;
					}
				}
			} else if (result == 2) {
				return 2;
			} else {
				return 3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 3;
	}

	public Integer putRoom(ArrayList<String> array) {
		BaseDAO base = new BaseDAO();
		try {
			int result = putFloor(array);
			int floor_sn = 0;
			if (result == 0) {
				base.sql = "select sn from tb_floor where sn=@@identity";
				base.stmt = base.conn.createStatement();
				base.rs = base.stmt.executeQuery(base.sql);
				if (base.rs.next()) {
					floor_sn = base.rs.getInt("sn");
					base.sql="insert into tb_room (floor_sn,name,remark) values (?,?,?)";
					base.pstmt=base.conn.prepareStatement(base.sql);
					base.pstmt.setInt(1, floor_sn);
					base.pstmt.setString(2, array.get(3));
					base.pstmt.setString(3, array.get(4));
					base.result=base.pstmt.executeUpdate();
					if(base.result>0){
						base.conn.commit();
						return 0;
					}else{
						base.conn.rollback();
						return 3;
					}
				} else {
					return 3;
				}
			}else if(result==1){
				
			}
			else if (result == 2) {
				return 2;
			} else {
				return 3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 3;
	}*/
	/*
	 * public BuildingBean selectBuildingByBuildingBean(ArrayList<String> bean){
	 * BuildingBean rBean=new BuildingBean(); BaseDAO base=new BaseDAO();
	 * base.sql="select * from tb_building where name=? and en=?"; try{
	 * base.pstmt=base.conn.prepareStatement(base.sql); base.pstmt.setString(1,
	 * bean.get(0)); base.pstmt.setString(2, bean.get(1));
	 * base.rs=base.pstmt.executeQuery(); if(base.rs.next()){
	 * rBean.setBuildingSn(base.rs.getInt("sn"));
	 * rBean.setBuildingName(base.rs.getString("name"));
	 * rBean.setBuildingEnName(base.rs.getString("en")); base.sql=
	 * "select * from tb_floor where building_sn=? and name=?";
	 * base.pstmt=base.conn.prepareStatement(base.sql); base.pstmt.setInt(1,
	 * rBean.getBuildingSn()); base.pstmt.setString(2, bean.get(2));
	 * base.rs=base.pstmt.executeQuery(); if(base.rs.next()){ FloorBean
	 * floor=new FloorBean(); floor.setFloorName(base.rs.getString("name"));
	 * floor.setFloorSn(base.rs.getInt("sn")); List<FloorBean> fList=new
	 * ArrayList(); base.sql="select * from tb_room where floor_sn=? and name=?"
	 * ; base.pstmt=base.conn.prepareStatement(base.sql); base.pstmt.setInt(1,
	 * floor.getFloorSn()); base.pstmt.setString(2, bean.get(3));
	 * base.rs=base.pstmt.executeQuery(); if(base.rs.next()){ RoomBean room=new
	 * RoomBean(); room.setRoomName(base.rs.getString("name"));
	 * room.setRoomSn(base.rs.getInt("sn"));
	 * room.setRoomRemark(base.rs.getString("remark")); List<RoomBean> rList=new
	 * ArrayList(); rList.add(room); floor.setRoomBeanList(rList);
	 * fList.add(floor); rBean.setFloorBeanList(fList); }else{
	 * floor.setRoomBeanList(null); fList.add(floor);
	 * rBean.setFloorBeanList(fList); } }else{ rBean.setFloorBeanList(null); }
	 * }else{ return null; } }catch(SQLException e){ e.printStackTrace(); }
	 * return rBean; } public void insertBuilding(ArrayList<String> array){
	 * BaseDAO base=new BaseDAO(); base.sql=
	 * "insert into tb_building (name,en) values (?,?);select sn from tb_building where sn=@@identity;"
	 * ; try { base.pstmt=base.conn.prepareStatement(base.sql);
	 * base.pstmt.setString(1, array.get(0)); base.pstmt.setString(2,
	 * array.get(1)); base.rs=base.pstmt.executeQuery(); if(base.rs.next()){ int
	 * building_sn=base.rs.getInt("sn"); insertFloor(array,building_sn); }else{
	 * base.conn.rollback(); } } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } } public void
	 * insertFloor(ArrayList<String> array,int building_sn){ BaseDAO base=new
	 * BaseDAO(); base.sql=
	 * "insert into tb_floor (building_sn,name) values (?,?);select sn from tb_floor where sn=@@identity;"
	 * ; try{ base.pstmt=base.conn.prepareStatement(base.sql);
	 * base.pstmt.setInt(1, building_sn); base.pstmt.setString(2,array.get(2));
	 * base.rs=base.pstmt.executeQuery(); if(base.rs.next()){ int
	 * floor_sn=base.rs.getInt("sn"); insertRoom(array,floor_sn); }else{
	 * base.conn.rollback(); } }catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } } public Integer
	 * insertRoom(ArrayList<String> array,int floor_sn){ BaseDAO base=new
	 * BaseDAO(); base.sql=
	 * "insert into tb_room (floor_sn,name,remark) values (?,?,?)"; try{
	 * base.pstmt=base.conn.prepareStatement(base.sql); base.pstmt.setInt(1,
	 * floor_sn); base.pstmt.setString(2, array.get(3)); base.pstmt.setString(3,
	 * array.get(4)); base.result=base.pstmt.executeUpdate(); if(base.result>0){
	 * base.conn.commit(); }else{ base.conn.rollback(); } }catch (SQLException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } return 0;
	 * }
	 */
}
