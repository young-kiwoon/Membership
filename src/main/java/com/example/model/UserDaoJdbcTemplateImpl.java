package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVo;

@Repository("userDao1")
public class UserDaoJdbcTemplateImpl implements UserDao {

	@Autowired
	//private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int create(UserVo user) {	
		//jdbcTemplate
		String sql = "INSERT INTO Users VALUES(?,?,?,?)";
		return this.jdbcTemplate.update(sql, user.getUserid(), user.getName(), user.getGender(), user.getCity());
	}

	@Override
	public UserVo read(String userid) {
		String sql = "SELECT * FROM Users WHERE userid = ?";
		UserVo user = this.jdbcTemplate.queryForObject(sql, new MyMapper() , userid);
		return user;
	}
	
	private class MyMapper implements RowMapper<UserVo>{
		@Override
		public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVo user = new UserVo();
			user.setUserid(rs.getString("userid"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setCity(rs.getString("city"));
			return user;
		}	
	}
	

	@Override
	public List<UserVo> readAll() {
		String sql = "SELECT * FROM Users ORDER BY userid DESC";
		return this.jdbcTemplate.query(sql, new MyMapper());
	}

	@Override
	public int update(UserVo user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String userid) {
		return this.jdbcTemplate.update("DELETE FROM Users WHERE userid = ?", userid);
	}

}
