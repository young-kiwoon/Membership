package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public int create(UserVo user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowcount = -1;
		try {
			conn = this.dataSource.getConnection();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO Users VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getCity());
			rowcount = pstmt.executeUpdate();
			conn.commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		finally {
			try {
				if(conn!=null) conn.close();
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rowcount;
	}

	@Override
	public UserVo read(String userid) {
		UserVo user = new UserVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.dataSource.getConnection();
			String sql = "SELECT * FROM Users WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			rs.next();
			user = new UserVo(rs.getString("userid"), rs.getString("name"), rs.getString("gender"), rs.getString("city"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} 
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<UserVo> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(UserVo user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
