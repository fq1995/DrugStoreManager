package com.fq.util;

import java.sql.*;

/*
 * 1.获取数据库联接
 * 2.关闭一些资源
 */
public class DBConnection {

	private String url = "jdbc:mysql://127.0.0.1:3306/struts07?useUnicode=true&characterEncoding=utf-8";
	private static String className = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "123456";

	static {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void relse(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		try {
			if (con!= null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}
}
