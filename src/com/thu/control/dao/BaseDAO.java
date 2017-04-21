package com.thu.control.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	private static String sql_url = "jdbc:mysql://localhost:3306/guagua?uicode=true&characterEncoding=UTF-8";
	private static String admin = "root";
	private static String pdw = "dear2324w";
	public static String sql;
	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs = null;
	public static int result = 0;
	BaseDAO(){
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(sql_url, admin, pdw);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("資料庫連接失敗!");
			e.printStackTrace();
		}
	}
	public void close() throws SQLException {
		conn.close();
		stmt.close();
		rs.close();
	}
}
