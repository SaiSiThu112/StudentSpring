package com.stu.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.stu.dto.StudentRes;

public class StudentRowmapper implements org.springframework.jdbc.core.RowMapper<StudentRes>{

	@Override
	public StudentRes mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		StudentRes res = new StudentRes();
		res.setId(String.valueOf(rs.getInt("s.id")));
		res.setName(rs.getString("s.name"));
		res.setDob(rs.getString("s.dob"));
		res.setGender(rs.getString("s.gender"));
		res.setPhone(rs.getString("s.phone"));
		res.setEducation(rs.getString("s.education"));
		res.setCourse(rs.getString("course"));
		
		return res;
	}

}
