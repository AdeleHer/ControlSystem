package com.thu.control.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thu.control.bean.CourseBean;
import com.thu.control.bean.RoomBean;

public class CourseDAO {
	public CourseBean selectCourseBySn(int sn){
		CourseBean bean=new CourseBean();
		BaseDAO base=new BaseDAO();
		base.sql="select * from tb_course where sn="+sn;
		try {
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			if(base.rs.next()){
				bean.setCourseSn(base.rs.getInt("sn"));
				bean.setCourseid(base.rs.getString("courseid"));
				bean.setName(base.rs.getString("name"));
			}else{
				return null;
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
		return bean;
	}
	public List<RoomBean> getRelCourseByRoomSn(List<RoomBean> beanList){
		BaseDAO base=new BaseDAO();
		for(int i=0;i<beanList.size();i++){
			RoomBean bean=beanList.get(i);
		base.sql="select * from rel_course_room where room_sn="+bean.getRoomSn();
		try {
			base.stmt=base.conn.createStatement();
			base.rs=base.stmt.executeQuery(base.sql);
			Set<Integer> courseSet=new HashSet();
			while(base.rs.next()){
				courseSet.add(base.rs.getInt("course_sn"));
			}
			bean.setScheduleCount(courseSet);
			beanList.set(i, bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanList;
	}
}
