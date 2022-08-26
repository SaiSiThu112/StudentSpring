package com.stu.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.stu.dto.CourseRes;


public class CourseRowmapper implements RowMapper<CourseRes>{

	@Override
	public CourseRes mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CourseRes res = new CourseRes();
		res.setId(String.valueOf(rs.getInt("id")));
		res.setName(rs.getString("name"));
		
		return res;
	}

}
