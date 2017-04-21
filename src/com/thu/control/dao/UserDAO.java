package com.thu.control.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thu.control.bean.UserBean;

public class UserDAO {
	public Map<Integer, UserBean> findAllUser() {
		Map<Integer, UserBean> rMap = new HashMap();
		BaseDAO base = new BaseDAO();
		List<UserBean> rList = new ArrayList();
		base.sql = "select sn,name,position from tb_user_profile";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			String groupSn;
			String GroupName;
			while (base.rs.next()) {
				int UserName = base.rs.getInt("sn");
				UserBean bean = new UserBean();
				bean.setSn(UserName);
				bean.setGroupString(base.rs.getString("position"));
				bean.setName(base.rs.getString("name"));
				rMap.put(UserName, bean);
			}
			for (int rKey : rMap.keySet()) {
				UserBean bean = rMap.get(rKey);
				bean.setGroup(new HashSet());
				if (bean.getGroupString().contains(",")) {
					String position = bean.getGroupString();
					String positions[] = position.split(",");
					for (int i = 0; i < positions.length; i++) {
						base.sql = "select name from tb_position where sn=" + positions[i];
						base.stmt = base.conn.createStatement();
						base.rs = base.stmt.executeQuery(base.sql);
						if (base.rs.next()) {
							Set GroupSet = bean.getGroup();
							GroupSet.add(base.rs.getString("name"));
							bean.setGroup(GroupSet);
						}
					}
				} else {
					String position = bean.getGroupString();
					if (position.length() > 0) {
						base.sql = "select name from tb_position where sn=" + position;
						base.stmt = base.conn.createStatement();
						base.rs = base.stmt.executeQuery(base.sql);
						if (base.rs.next()) {
							Set GroupSet = bean.getGroup();
							GroupSet.add(base.rs.getString("name"));
							bean.setGroup(GroupSet);
						}
					}
				}
				rMap.put(rKey, bean);
			}
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("搜尋失敗!");
			e.printStackTrace();
			return null;
		}
		return rMap;
	}

	public UserBean findUserBySn(int sn) {
		BaseDAO base = new BaseDAO();
		UserBean bean = new UserBean();
		base.sql = "select tb_user.*,tb_user_profile.name,tb_user_profile.phone,tb_user_profile.position from (select * from tb_user where sn="
				+ sn + ")as tb_user left join tb_user_profile on tb_user.sn=tb_user_profile.sn";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			if (base.rs.next()) {
				bean.setAccount(base.rs.getString("tb_user.account"));
				bean.setPassword(base.rs.getString("tb_user.password"));
				bean.setName(base.rs.getString("tb_user_profile.name"));
				bean.setSn(base.rs.getInt("tb_user.sn"));
				bean.setPhone(base.rs.getString("tb_user_profile.phone"));
				bean.setGroupString(base.rs.getString("tb_user_profile.position"));
			}
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("搜尋失敗!");
			e.printStackTrace();
			return null;
		}
		return bean;
	}

	public Map findAllPosition() {
		BaseDAO base = new BaseDAO();
		Map rMap = new HashMap();
		base.sql = "select * from tb_position";
		try {
			base.stmt = base.conn.createStatement();
			base.rs = base.stmt.executeQuery(base.sql);
			while (base.rs.next()) {
				rMap.put(base.rs.getInt("sn"), base.rs.getString("name"));
			}
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("搜尋失敗!");
			e.printStackTrace();
			return null;
		}
		return rMap;
	}

	public int insertUser(UserBean bean) {
		BaseDAO base = new BaseDAO();
		base.sql = "insert into tb_user (account,password) values (?,?)";
		int UserSn = 0;
		try {
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, bean.getAccount());
			base.pstmt.setString(2, bean.getPassword());
			base.result = base.pstmt.executeUpdate();
			if (base.result > 0) {
				base.sql = "select sn from tb_user where account=?";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setString(1, bean.getAccount());
				base.rs = base.pstmt.executeQuery();
				if (base.rs.next()) {
					UserSn = base.rs.getInt("sn");
				} else {
					base.conn.rollback();
				}
				base.sql = "insert into tb_user_profile (sn,name,phone,position) values (?,?,?,?)";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setInt(1, UserSn);
				base.pstmt.setString(2, bean.getName());
				base.pstmt.setString(3, bean.getPhone());
				base.pstmt.setString(4, bean.getGroupString());
				base.result = base.pstmt.executeUpdate();
				if (base.result > 0) {
					base.conn.commit();
					base.close();
					return 1;
				} else {
					base.conn.rollback();
				}
			}
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("搜尋失敗!");
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public List<UserBean> findUserByAccount(String account) {
		List<UserBean> beanList = new ArrayList();
		UserBean bean = new UserBean();
		BaseDAO base = new BaseDAO();
		base.sql = "select * from tb_user where account=?";
		try {
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, account);
			base.rs = base.pstmt.executeQuery();
			if (base.rs.next()) {
				bean.setAccount(base.rs.getString("account"));
				bean.setSn(base.rs.getInt("sn"));
				bean.setPassword(base.rs.getString("password"));
				beanList.add(bean);
			}
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanList;
	}

	public int updateUser(UserBean bean) {
		BaseDAO base = new BaseDAO();
		try {
			base.sql = "update tb_user set account=? ,password=? where sn=?";
			base.pstmt = base.conn.prepareStatement(base.sql);
			base.pstmt.setString(1, bean.getAccount());
			base.pstmt.setString(2, bean.getPassword());
			base.pstmt.setInt(3, bean.getSn());
			base.result = base.pstmt.executeUpdate();
			if (base.result > 0) {
				base.sql = "update tb_user_profile set name=? ,phone=? ,position=? where sn=?";
				base.pstmt = base.conn.prepareStatement(base.sql);
				base.pstmt.setString(1, bean.getName());
				base.pstmt.setString(2, bean.getPhone());
				base.pstmt.setString(3, bean.getGroupString());
				base.pstmt.setInt(4, bean.getSn());
				base.result = base.pstmt.executeUpdate();
				if (base.result > 0) {
					base.conn.commit();
					base.close();
					return 1;
				} else {
					base.conn.rollback();
					base.close();
				}
			} else {
				base.conn.rollback();
				base.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				base.conn.rollback();
				base.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteUser(int sn) {
		BaseDAO base = new BaseDAO();
		base.sql = "delete from tb_user_profile where sn=" + sn;
		try {
			base.stmt = base.conn.createStatement();
			base.result = base.stmt.executeUpdate(base.sql);
			if (base.result > 0) {
				base.sql = "delete from rel_course_user where user_sn=" + sn;
				base.stmt = base.conn.createStatement();
				base.result = base.stmt.executeUpdate(base.sql);
				base.sql = "update tb_application set user_sn='' where user_sn=" + sn;
				base.stmt = base.conn.createStatement();
				base.result = base.stmt.executeUpdate(base.sql);
				base.sql = "delete from tb_user where sn=" + sn;
				base.stmt = base.conn.createStatement();
				base.result = base.stmt.executeUpdate(base.sql);
				if (base.result > 0) {
					base.conn.commit();
					return base.result;
				} else {
					base.conn.rollback();
					return 0;
				}
			} else {
				base.conn.rollback();
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				base.conn.rollback();
				base.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return 0;
	}
}
