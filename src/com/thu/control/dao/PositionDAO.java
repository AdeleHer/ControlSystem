package com.thu.control.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.thu.control.bean.PositionBean;

public class PositionDAO {
	public int insertPosition(String name) {
		BaseDAO base = new BaseDAO();
		base.sql = "insert into tb_position (name) values (?)";
		try {
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, name);
			base.result = base.pstmt.executeUpdate();
			if (base.result > 0) {
				base.conn.commit();
				return base.result;
			} else {
				base.conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updatePosition(PositionBean bean) {
		BaseDAO base = new BaseDAO();
		base.sql = "update tb_position set name=? where sn=?";
		try {
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, bean.getName());
			base.pstmt.setInt(2, bean.getSn());
			base.result = base.pstmt.executeUpdate();
			if (base.result > 0) {
				base.conn.commit();
				return base.result;
			} else {
				base.conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public PositionBean findPositionBySn(int sn) {
		PositionBean bean = new PositionBean();
		BaseDAO base = new BaseDAO();
		base.sql = "select * from tb_position where sn=" + sn;
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			if (base.rs.next()) {
				bean.setSn(base.rs.getInt("sn"));
				bean.setName(base.rs.getString("name"));
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
		return bean;
	}

	public int deletePosition(int sn) {
		BaseDAO base = new BaseDAO();
		base.sql = "delete from tb_position where sn=" + sn;
		try {
			base.stmt = base.conn.createStatement();
			base.result = base.stmt.executeUpdate(base.sql);
			if (base.result > 0) {
				base.conn.commit();
				return base.result;
			} else {
				base.conn.rollback();
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
		return 0;
	}

	public ArrayList updateDeletePositionEffect(int sn) {
		ArrayList<Map<String,String>> position = new ArrayList();
		ArrayList rArray=new ArrayList();
		BaseDAO base = new BaseDAO();
		base.sql = "select sn,position from tb_user_profile";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			while (base.rs.next()) {
				Map<String,String> m=new HashMap();
				m.put("sn", base.rs.getInt("sn")+"");
				m.put("position",base.rs.getString("position"));
				position.add(m);
			}
			for (int i = 0; i < position.size(); i++) {
				if (position.get(i).get("position").contains(",")) {
					String positions[] = position.get(i).get("position").split(",");
					for(int j=0;j<positions.length;j++){
						if(positions[j].equals(sn+"")){
							System.out.println(position.get(i).get("sn"));
							rArray.add(position.get(i).get("sn"));
						}
					}
				} else {
					if(position.get(i).get("position").equals(sn+"")){
						System.out.println(position.get(i).get("sn"));
						rArray.add(position.get(i).get("sn"));
					}
				}
			}
			for(int i=0;i<rArray.size();i++){
				base.sql="select position from tb_user_profile where sn="+rArray.get(i);
				base.stmt=base.conn.createStatement();
				base.rs=base.stmt.executeQuery(base.sql);
				if(base.rs.next()){
					String str=base.rs.getString("position");
					Set p_set=new HashSet();
					if(str.contains(",")){
						String positions[] =str.split(",");
						for(int j=0;j<positions.length;j++){
							if(!positions[j].equals(sn+"")){
								p_set.add(positions[j]);
							}
						}
						str=p_set.toString().substring(1,p_set.toString().length()-1);
					}else{
						if(str.equals(sn+"")){
							str="";
						}
					}
					base.sql="update tb_user_profile set position=? where sn=?";
					base.pstmt=base.conn.prepareStatement(base.sql);
					base.pstmt.setString(1, str);
					base.pstmt.setInt(2, sn);
					base.result=base.pstmt.executeUpdate();
					if(base.result>0){
						base.conn.commit();
					}else{
						base.conn.rollback();
					}
				}
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
		return rArray;
	}
}
