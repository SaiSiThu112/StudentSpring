package com.stu.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.stu.dto.UserRes;

public class UserRowmapper implements RowMapper<UserRes> {

	@Override
	public UserRes mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserRes res = new UserRes();
		res.setId(String.valueOf(rs.getInt("id")));
		res.setName(rs.getString("name"));
		res.setEmail(rs.getString("email"));
		res.setPassword(rs.getString("password"));
		res.setRole(rs.getString("role"));
		return res;
	}

}
