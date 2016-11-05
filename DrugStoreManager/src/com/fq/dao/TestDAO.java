package com.fq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fq.po.TestBean;
import com.fq.util.DBConnection;

public class TestDAO {
	
	DBConnection dbcon = new DBConnection();
	public void add(TestBean bean){
		String sql="insert into tbl_test(title,old_name,new_name) values(?,?,?)";
		Connection con=dbcon.getCon();
		PreparedStatement pstmt=null;
		int row=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,bean.getTitle());
			pstmt.setString(2,bean.getOldName());
			pstmt.setString(3,bean.getNewName());
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbcon.relse(con, pstmt, null);
		}
	}
	
	public List<TestBean> all(){
		String sql= "select * from tbl_test";
		Connection con=dbcon.getCon();
		Statement stmt=null;
		ResultSet rs=null;
		List<TestBean> ls = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				TestBean bean = new TestBean();
				bean.setId(rs.getInt("id"));
				bean.setOldName(rs.getString("old_name"));
				bean.setNewName(rs.getString("new_name"));
				bean.setTitle(rs.getString("title"));
				ls.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.relse(con, stmt, rs);
		}
		return ls;
	}
}
